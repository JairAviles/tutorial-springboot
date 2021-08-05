package com.tutorial.springboot.bean;

public class MyBeanOtherImpl implements MyBean {
  @Override
  public void print() {
    System.out.println("Hello from my second bean implementation!");
  }
}
