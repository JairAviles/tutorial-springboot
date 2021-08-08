package com.tutorial.springboot.bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBeanOtherImpl implements MyBean {
  @Override
  public void print() {
    log.info("Printing from MyBeanOtherImpl");
    System.out.println("Hello from my second bean implementation!");
  }
}
