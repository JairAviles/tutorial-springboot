package com.tutorial.springboot.bean;

public class MyBeanWithDependencyImpl implements MyBeanWithDependecy {

  private MyOperation myOp;

  public MyBeanWithDependencyImpl(MyOperation myOp) {
    this.myOp = myOp;
  }

  @Override
  public void printWithDependency() {
    int number = 1;
    System.out.println(myOp.sum(number));
    System.out.println("Hello from bean with dependency implementation!");
  }
}
