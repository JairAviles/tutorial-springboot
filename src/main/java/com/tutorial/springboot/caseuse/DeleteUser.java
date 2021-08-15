package com.tutorial.springboot.caseuse;

import com.tutorial.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {
  @Autowired
  private UserService userService;

  public void remove(Long id) {
    userService.delete(id);
  }
}
