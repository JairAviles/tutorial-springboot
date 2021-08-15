package com.tutorial.springboot.config;

import com.tutorial.springboot.caseuse.GetUser;
import com.tutorial.springboot.caseuse.GetUserImpl;
import com.tutorial.springboot.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfig {
  @Bean
  GetUser getUser() {
    return new GetUserImpl();
  }
}
