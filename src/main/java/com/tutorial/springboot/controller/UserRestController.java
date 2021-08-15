package com.tutorial.springboot.controller;

import com.tutorial.springboot.caseuse.GetUser;
import com.tutorial.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
  // CREATE, GET, DELETE, UPDATE
  @Autowired
  private GetUser getuser;

  @GetMapping("/")
  List<User> get() {
    return getuser.getAll();
  }
}
