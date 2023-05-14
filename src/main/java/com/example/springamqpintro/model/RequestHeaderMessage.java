package com.example.springamqpintro.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestHeaderMessage {
  private Map<String, Object> headers;
  private MyMessage message;
}
