package com.example.springamqpintro.model;

import java.util.HashMap;
import java.util.Map;

public class MyHeaders {
  private Map<String, Object> headers = new HashMap<>();

  public void put(String key, Object value) {
    headers.put(key, value);
  }

  public Map<String, Object> toMap() {
    return headers;
  }
}