package ru.ssau.citizen.controllers;

import net.bytebuddy.utility.RandomString;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.citizen.DTO.ForgotPasswordDTO;
import ru.ssau.citizen.DTO.ResetPasswordDTO;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.service.ActorServiceImp;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
public class ForgotPasswordController {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ActorServiceImp customerService;


    @PostMapping("/forgot_password")
    public ResponseEntity processForgotPassword(@RequestBody ForgotPasswordDTO request) {

        //String email= request.getParameter("email");
        String email= request.getEmail();
        String token = RandomString.make(30);

        try {
            customerService.updateResetPasswordToken(token, email);
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
    public String processResetPassword(@RequestBody ResetPasswordDTO request) {


        String msg;

        Actor customer = customerService.getByResetPasswordToken(request.getToken());

        if (customer == null) {

            msg = "Invalid Token";
            return msg;
        } else {
            customerService.updatePassword(customer, request.getPassword());
            msg = "You have successfully changed your password.";
        }

        return msg;
    }
}
