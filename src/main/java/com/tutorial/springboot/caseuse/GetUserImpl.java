package com.tutorial.springboot.caseuse;

import com.tutorial.springboot.entity.User;
import com.tutorial.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GetUserImpl implements GetUser {

  @Autowired
  private UserService userService;

  @Override
  public List<User> getAll() {
    return userService.getAllUsers();
  }
}
