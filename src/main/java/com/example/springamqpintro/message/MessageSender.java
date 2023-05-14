package com.example.springamqpintro.message;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springamqpintro.model.MyMessage;


@Component
public class MessageSender {
  
  private final RabbitTemplate rabbitTemplate;
  private final AmqpAdmin amqpAdmin;

  public MessageSender(RabbitTemplate rabbitTemplate, AmqpAdmin amqpAdmin) {
    this.rabbitTemplate = rabbitTemplate;
    this.amqpAdmin = amqpAdmin;
  }

  public void sendToQueue(String queue, MyMessage message) {
    rabbitTemplate.convertAndSend(queue, message);
    System.out.println(" [x] Sent '" + message + "'" + " to queue: " + queue);
  }

  public void sendDirect(String exchange, String routingKey, MyMessage message) {
    declareDirectExchange(exchange);
    rabbitTemplate.convertAndSend(exchange, routingKey, message);
    System.out.println("[x] Sent '" + message + "' to exchange: " + exchange + ", routing key: " + routingKey);
  }

  public void sendFanout(String exchange, MyMessage message) {
    declareFanoutExchange(exchange);
    rabbitTemplate.convertAndSend(exchange, "", message);
    System.out.println("[x] Sent '" + message + "' to fanout exchange: " + exchange);
  }


  //
  private void declareDirectExchange(String exchange) {
    Exchange exchangeObj = new DirectExchange(exchange);
    amqpAdmin.declareExchange(exchangeObj);
  }

  private void declareFanoutExchange(String exchange) {
    Exchange exchangeObj = new FanoutExchange(exchange);
    amqpAdmin.declareExchange(exchangeObj);
  }

  private void declareTopicExchange(String exchange) {
    Exchange exchangeObj = new TopicExchange(exchange);
    amqpAdmin.declareExchange(exchangeObj);
  }

  private void declareHeadersExchange(String exchange) {
    Exchange exchangeObj = new HeadersExchange(exchange);
    amqpAdmin.declareExchange(exchangeObj);
  }
}
