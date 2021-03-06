package com.tutorial.springboot.service;

import com.tutorial.springboot.entity.User;
import com.tutorial.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Transactional
  public void saveTransactional(List<User> users) {
    users.stream()
        .peek(user -> log.info("Inserted user: " + user))
        .forEach(userRepository::save);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  public User update(User user, Long id) {
     return userRepository.findById(id)
        .map(
            updatedUser -> {
              updatedUser.setEmail(user.getEmail());
              updatedUser.setBirthDate(user.getBirthDate());
              updatedUser.setName(user.getName());
              return userRepository.save(updatedUser);
            }
        ).get();
  }
}
