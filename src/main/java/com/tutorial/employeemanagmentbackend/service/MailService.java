package com.tutorial.employeemanagmentbackend.service;

import com.tutorial.employeemanagmentbackend.exceptions.BusinessException;
import com.tutorial.employeemanagmentbackend.exceptions.ErrorCode;
import com.tutorial.employeemanagmentbackend.model.GreetingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {

    Logger logger = LoggerFactory.getLogger(MailService.class);

    public void sendEmail(GreetingRequest greetingRequest) {
        String to = "pozdravimeinternal@gmail.com";
        String subject = "Poruchka-" + greetingRequest.getActor();
        String text = greetingRequest.getGreetingText() + "\n email: " + greetingRequest.getEmail();

        String from = "pozdravime321@outlook.com";
        String password = "123456789Hh..";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            logger.info("Email send successfully {}", message);
        } catch (MessagingException e) {
            logger.error("Cannot send mail {}", e.getMessage(), e);
            throw new BusinessException(ErrorCode.CANNOT_SEND_EMAIL, "Cannot send mail.");
        }
    }
}