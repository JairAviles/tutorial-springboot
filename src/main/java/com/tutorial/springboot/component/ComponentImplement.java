package com.tutorial.springboot.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependency {
  @Override
  public void greet() {
    System.out.println("Hello world from component!");
  }
}
