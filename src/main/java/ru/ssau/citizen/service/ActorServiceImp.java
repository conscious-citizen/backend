package ru.ssau.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ssau.citizen.config.jwt.JwtUtils;
import ru.ssau.citizen.dto.JwtResponseDto;
import ru.ssau.citizen.dto.LoginDto;
import ru.ssau.citizen.dto.MessageResponse;
import ru.ssau.citizen.dto.RegistrationDto;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.entities.ERole;
import ru.ssau.citizen.entities.Role;
import ru.ssau.citizen.exception.GlobalException;
import ru.ssau.citizen.repository.ActorRepository;
import ru.ssau.citizen.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActorServiceImp implements ActorService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  JwtUtils jwtUtils;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;;

    @Override
    public JwtResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponseDto(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }

    @Override
    public MessageResponse registration(RegistrationDto registrationDto) {
        if (actorRepository.existsByLogin(registrationDto.getUsername()))
            throw new GlobalException("Ошибка: Данный пользователь уже зарегистрирован!", HttpStatus.BAD_REQUEST);

        Actor user = new Actor(registrationDto.getEmail(),registrationDto.getCity(),registrationDto.getStreet(),
                registrationDto.getHouse(),registrationDto.getApartment(),
                registrationDto.getUsername(),
                passwordEncoder.encode(registrationDto.getPassword()));

        String requestRole = registrationDto.getRole();
        Set<Role> roles = new HashSet<>();

        switch (requestRole) {
            case "ROLE_USER":
                Role roleStudent = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new GlobalException("Роль 'Ученик' не найдена", HttpStatus.BAD_REQUEST));
                roles.add(roleStudent);
                break;
            case "ROLE_ADMIN":
                Role roleTeacher = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new GlobalException("Роль 'Учитель' не найдена", HttpStatus.BAD_REQUEST));
                roles.add(roleTeacher);
                break;
        }
        user.setRoles(roles);
        actorRepository.save(user);
        return new MessageResponse("Пользователь " + user.getLogin() + " успешно зарегистрирован!");
    }
    @Override
    public Actor findActorByLogin(String login) {
        return actorRepository.findActorByLogin(login);
    }


    public void updateResetPasswordToken(String token, String email) throws Exception {
        Actor customer = actorRepository.findActorByEmail(email);
        if (customer != null) {
            customer.setResetPasswordToken(token);
            actorRepository.save(customer);
        } else {
            throw new Exception("Could not find any customer with the email " + email);
        }
    }

    public Actor getByResetPasswordToken(String token) {
        return actorRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(Actor customer, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);

        customer.setResetPasswordToken(null);
        actorRepository.save(customer);
    }
}
