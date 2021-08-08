package com.tutorial.springboot.config;

import com.tutorial.springboot.bean.MyBeanWithProperties;
import com.tutorial.springboot.bean.MyBeanWithPropertiesImpl;
import com.tutorial.springboot.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfig {
  @Value("${value.name}")
  private String name;

  @Value("${value.lastName}")
  private String lastName;

  @Value("${value.random}")
  private String random;

  @Bean
  public MyBeanWithProperties fun() {
    return new MyBeanWithPropertiesImpl(name, lastName);
  }
}
