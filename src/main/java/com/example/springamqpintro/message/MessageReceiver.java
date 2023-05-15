package com.example.springamqpintro.message;

import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.springamqpintro.model.MyMessage;

@Component
public class MessageReceiver {

  // @RabbitListener(queues = "q.test1")
  // public void receiveFromQueue(MyMessage message) {
  // System.out.println(" [-] Received Queue'" + message.getName() + "'");
  // }

  // Direct Exchange
  @RabbitListener(bindings = @QueueBinding(
    value = @Queue,
    exchange = @Exchange(value = "ex.test.direct", type = "direct", durable = "true"),
    key = "rk1")
  )
  public void receiveDirect(MyMessage message) {
    System.out.println(" [-] Received Direct '" + message.getName() + "'");
  }

  // Fanout Exchange
  @RabbitListener(bindings = @QueueBinding(
    value = @Queue, 
    exchange = @Exchange(value = "ex.test.fanout", type = "fanout", durable = "true"))
  )
  public void receiveFanout(MyMessage message) {
    System.out.println(" [-] Received Fanout '" + message.getName() + "'");
  }

  // Topic Exchange
  @RabbitListener(bindings = @QueueBinding(
    value = @Queue,
    exchange = @Exchange(value = "ex.test.topic", type = "topic", durable = "true"),
    key = "#.sport.#")
  )
  public void receiveTopic(MyMessage message) {
    System.out.println(" [-] Received Topic '" + message.getName() + "'");
  }

  // Headers Exchange
  @RabbitListener(bindings = @QueueBinding(
    value = @Queue,
    exchange = @Exchange(value = "ex.test.header", type = "headers", durable = "true"),
    arguments = {@Argument(name = "sport", value = "true"),@Argument(name = "age", value = "20")})
  )
  public void receiveHeaders(MyMessage message) {
    System.out.println(" [-] Received Headers '" + message.getName() + "'");
  }

}
