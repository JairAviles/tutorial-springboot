package com.tutorial.springboot.bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBeanImpl implements MyBean {
  @Override
  public void print() {
    log.info("Printing from MyBeanImpl");
    System.out.println("Hello from my bean implementation!");
  }
}
