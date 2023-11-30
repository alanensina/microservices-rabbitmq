package com.ms.user.producers;

import com.ms.user.dtos.EmailRecordDTO;
import com.ms.user.models.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Exchange Type default: the Routing Key must be the same name of the queue created
     */
    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishEmailMessage(User user){

        var emailDTO = new EmailRecordDTO(
                user.getId(),
                user.getEmail(),
                "User registered successfully!",
                "Hello " + user.getName() + ". Your registration was successful! \nWelcome!");

        // Use an empty String to exchange type default.
        rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }
}
