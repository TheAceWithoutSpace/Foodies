package com.example.demo.email;

import com.example.demo.security.jwt.JwtConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService implements EmailSender{
    private final JavaMailSender mailSender;
    private final JwtConfig jwtConfig;
    @Override
    @Async

    public void send(String to, String email) {
        try{
            MimeMessage mimeMessage= mailSender.createMimeMessage();
            MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(email,true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom(jwtConfig.getFromEmail());
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            log.error("failed to send email",e);
            throw new IllegalStateException("Failed to send Email");
        }
    }
}
