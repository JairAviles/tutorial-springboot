package com.tutorial.springboot.caseuse;

import com.tutorial.springboot.entity.User;
import com.tutorial.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
  @Autowired
  private UserService userService;

  public User save(User user) {
    return userService.save(user);
  }
}
