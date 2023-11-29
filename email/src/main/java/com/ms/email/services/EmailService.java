package com.ms.email.services;

import com.ms.email.dtos.EmailRecordDTO;
import com.ms.email.enums.StatusEmail;
import com.ms.email.models.Email;
import com.ms.email.repositories.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailRepository repository;
    private final JavaMailSender sender;

    @Value(value = "${spring.mail.username}")
    private String senderName;

    public EmailService(EmailRepository repository, JavaMailSender sender) {
        this.repository = repository;
        this.sender = sender;
    }

    @Transactional
    public void sendEmail(EmailRecordDTO payload) {
        var email = new Email();
        BeanUtils.copyProperties(payload, email);

        try{
            email.setSendDate(LocalDateTime.now());
            email.setEmailFrom(senderName);

            var message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            sender.send(message);

            email.setStatus(StatusEmail.SENT);
        }catch(MailException e){
            email.setStatus(StatusEmail.ERROR);
        }finally{
            repository.save(email);
        }
    }
}
