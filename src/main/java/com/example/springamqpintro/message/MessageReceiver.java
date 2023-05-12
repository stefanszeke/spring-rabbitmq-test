package com.example.springamqpintro.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.springamqpintro.model.MyMessage;

@Component
public class MessageReceiver {
  
  // @RabbitListener(queues = "q.test")
  // public void receive(MyMessage message) {
  //   System.out.println(" [x] Received '" + message.getName() + "'");
  // }
}
