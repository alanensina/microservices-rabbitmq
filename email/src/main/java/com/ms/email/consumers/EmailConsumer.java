package com.ms.email.consumers;

import com.ms.email.dtos.EmailRecordDTO;
import com.ms.email.models.Email;
import com.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService service;

    public EmailConsumer(EmailService service) {
        this.service = service;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDTO payload){
        service.sendEmail(payload);
    }
}
