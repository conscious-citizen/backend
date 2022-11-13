package ru.ssau.citizen.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.bytebuddy.utility.RandomString;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import ru.ssau.citizen.dto.ForgotPasswordDTO;
import ru.ssau.citizen.dto.ResetPasswordDTO;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.service.ActorServiceImp;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@Tag(name = "Сброс пароля", description = "Все методы для работы со сбросом пароля")
public class ForgotPasswordController {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ActorServiceImp actorService;


    @PostMapping("/forgot_password")
    @Operation(summary = "Сброс пароля и отправка на почту ссылки для обновления пароля")
    public ResponseEntity processForgotPassword(@RequestBody ForgotPasswordDTO request) {

        //String email= request.getParameter("email");
        String email= request.getEmail();
        String token = RandomString.make(30);

        try {
            actorService.updateResetPasswordToken(token, email);
            String siteURL = request.getUrl();

            String resetPasswordLink = siteURL + "/reset_password?token=" + token;

            sendEmail(email, resetPasswordLink);


        } catch (Exception ex) {
            return new ResponseEntity("error mail send", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity("OK! We have sent a reset password link to your email. Please check.", HttpStatus.OK);
    }


    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {

        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom("conscious.scitizen.help@yandex.ru");
        simpleMail.setTo(recipientEmail);
        simpleMail.setSubject("Here's the link to reset your password");
        simpleMail.setText("Hello You have requested to reset your password. Click the link below to change your password:"
                + link +" Ignore this email if you do remember your password, or you have not made the request.");


        mailSender.send(simpleMail);
    }




    @PostMapping("/forgot_password/reset_password")
    @Operation(summary = "Обновление пароля")
    public String processResetPassword(@RequestBody ResetPasswordDTO request) {


        String msg;

        Actor customer = actorService.getByResetPasswordToken(request.getToken());

        if (customer == null) {

            msg = "Invalid Token";
            return msg;
        } else {
            actorService.updatePassword(customer, request.getPassword());
            msg = "You have successfully changed your password.";
        }

        return msg;
    }
}
