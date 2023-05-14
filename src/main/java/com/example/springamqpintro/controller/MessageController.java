package com.example.springamqpintro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springamqpintro.message.MessageSender;
import com.example.springamqpintro.model.MyHeaders;
import com.example.springamqpintro.model.MyMessage;
import com.example.springamqpintro.model.RequestHeaderMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

@RestController
public class MessageController {

  private final MessageSender messageSender;

  public MessageController(MessageSender messageSender) {
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

  @PostMapping("/sendTopic")
  public String sendTopic(
      @RequestParam("exchange") String exchange,
      @RequestParam("routingKey") String routingKey,
      @RequestBody MyMessage myMessage) {
    messageSender.sendTopic(exchange, routingKey, myMessage);
    return "Topic Message sent";
  }

  @PostMapping("/sendHeaders")
  public ResponseEntity<String> sendMessageWithHeaders(
          @RequestParam("exchange") String exchange,
          @RequestBody RequestHeaderMessage requestBody) {
  
      Map<String, Object> headers = requestBody.getHeaders();
      MyMessage message = requestBody.getMessage();
  
      messageSender.sendHeaders(exchange, message, headers);
  
      return ResponseEntity.status(HttpStatus.CREATED).body("Message sent with headers");
  }

}
