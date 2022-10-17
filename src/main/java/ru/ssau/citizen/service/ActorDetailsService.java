package ru.ssau.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.entities.Role;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ActorDetailsService implements UserDetailsService {

    @Autowired
    private ActorService actorService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Actor actor = actorService.findActorByLogin(userName);
        List<GrantedAuthority> authorities = getUserAuthority(actor.getRole());
        return buildUserForAuthentication(actor, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Role userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRoles.getName()));
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(Actor user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, authorities);
    }

}
