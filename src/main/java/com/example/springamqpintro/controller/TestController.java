package com.example.springamqpintro.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

  @PostMapping("/sendToQueue")
  public String sendToQueue(@RequestParam("queue") String queue, @RequestBody MyMessage myMessage) {
    messageSender.sendToQueue(queue, myMessage);
    return "Message sent";
  }

  @PostMapping("/sendDirect")
  public String sendDirect(
      @RequestParam("exchange") String exchange,
      @RequestParam("routingKey") String routingKey,
      @RequestBody MyMessage myMessage) {
    messageSender.sendDirect(exchange, routingKey, myMessage);
    return "Direct Message sent";
  }

  @PostMapping("/sendFanout")
  public String sendFanout(
      @RequestParam("exchange") String exchange,
      @RequestBody MyMessage myMessage) {
    messageSender.sendFanout(exchange, myMessage);
    return "Fanout Message sent";
  }
}
