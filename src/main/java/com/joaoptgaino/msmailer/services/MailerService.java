package com.joaoptgaino.msmailer.services;

import com.joaoptgaino.msmailer.enums.StatusEmail;
import com.joaoptgaino.msmailer.models.MailerModel;
import com.joaoptgaino.msmailer.repositories.MailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MailerService {
    @Autowired
    MailerRepository mailerRepository;

    @Autowired
    private JavaMailSender mailSender;

    public MailerModel sendEmail(MailerModel mailerModel) {
        mailerModel.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailerModel.getEmailFrom());
            message.setTo(mailerModel.getEmailTo());
            message.setSubject(mailerModel.getSubject());
            message.setText(mailerModel.getText());

            mailSender.send(message);
            mailerModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            mailerModel.setStatusEmail(StatusEmail.ERROR);
            e.printStackTrace();
        } finally {
            return mailerRepository.save(mailerModel);
        }
    }
}
