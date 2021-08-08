package com.tutorial.springboot.bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBeanWithDependencyImpl implements MyBeanWithDependecy {

  private MyOperation myOp;

  public MyBeanWithDependencyImpl(MyOperation myOp) {
    this.myOp = myOp;
  }

  @Override
  public void printWithDependency() {
    log.info("Printing from MyBeanWithDependencyImpl");
    int number = 1;
    System.out.println(myOp.sum(number));
    System.out.println("Hello from bean with dependency implementation!");
  }
}
