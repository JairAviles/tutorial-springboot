package com.tutorial.springboot.controller;

import com.tutorial.springboot.caseuse.CreateUser;
import com.tutorial.springboot.caseuse.DeleteUser;
import com.tutorial.springboot.caseuse.GetUser;
import com.tutorial.springboot.caseuse.UpdateUser;
import com.tutorial.springboot.entity.User;
import com.tutorial.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
  // CREATE, GET, DELETE, UPDATE
  @Autowired
  private GetUser getuser;

  @Autowired
  private CreateUser createUser;

  @Autowired
  private DeleteUser deleteUser;

  @Autowired
  private UpdateUser updateUser;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/")
  List<User> get() {
    return getuser.getAll();
  }

  @PostMapping("/")
  ResponseEntity<User> newUser(@RequestBody User user) {
    return new ResponseEntity<>(createUser.save(user), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  ResponseEntity deleteUser(@PathVariable Long id) {
    deleteUser.remove(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  ResponseEntity<User> replaceUser(@RequestBody User user, @PathVariable Long id) {
    return new ResponseEntity<>(updateUser.update(user, id), HttpStatus.OK);
  }

  @GetMapping("/pageable")
  List<User> getUserPageable(@RequestParam int page, @RequestParam int size) {
    return userRepository.findAll(PageRequest.of(page, size)).getContent();
  }

}
