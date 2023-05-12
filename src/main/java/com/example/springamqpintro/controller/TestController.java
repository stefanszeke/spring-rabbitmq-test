package com.example.springamqpintro.controller;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springamqpintro.message.MessageSender;
import com.example.springamqpintro.model.MyMessage;
import com.fasterxml.jackson.databind.ObjectMapper;




@RestController
public class TestController {

  private final MessageSender messageSender;

  public TestController(MessageSender messageSender) {
      this.messageSender = messageSender;
  }

  @PostMapping("/send/{queue}")
  public String sendMessage(@RequestBody MyMessage myMessage) {
    messageSender.send("q.test", myMessage);
    return "Message sent";
  }
}
