package com.tutorial.springboot.bean;

public class MyBeanImpl implements MyBean {
  @Override
  public void print() {
    System.out.println("Hello from my bean implementation!");
  }
}
