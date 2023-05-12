package com.example.springamqpintro.message;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springamqpintro.model.MyMessage;


@Component
public class MessageSender {
  
  private final RabbitTemplate rabbitTemplate;

  @Autowired
  public MessageSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void send(String queue, MyMessage message) {
    rabbitTemplate.convertAndSend(queue, message);
    System.out.println(" [x] Sent '" + message + "'" + " to queue: " + queue);
  }
}
