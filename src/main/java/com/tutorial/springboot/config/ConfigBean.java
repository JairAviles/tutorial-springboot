package com.tutorial.springboot.config;

import com.tutorial.springboot.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {
  @Bean
  public MyBean myBean() {
    return new MyBeanOtherImpl();
  }

  @Bean
  public MyOperation myOperation() {
    return new MyOperationImpl();
  }

  @Bean
  public MyBeanWithDependecy myBeanWithDependecy(MyOperation myOperation) {
    return new MyBeanWithDependencyImpl(myOperation);
  }
}
