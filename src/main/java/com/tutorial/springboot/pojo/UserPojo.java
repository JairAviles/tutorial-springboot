package com.tutorial.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "user")
@ConstructorBinding
@AllArgsConstructor
@Getter
@Setter
public class UserPojo {
  private String email;
  private String password;
  private int age;


}
