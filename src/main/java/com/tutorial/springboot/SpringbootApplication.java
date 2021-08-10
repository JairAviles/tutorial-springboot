package com.tutorial.springboot;

import com.tutorial.springboot.bean.MyBean;
import com.tutorial.springboot.bean.MyBeanWithDependecy;
import com.tutorial.springboot.bean.MyBeanWithProperties;
import com.tutorial.springboot.component.ComponentDependency;
import com.tutorial.springboot.entity.User;
import com.tutorial.springboot.pojo.UserPojo;
import com.tutorial.springboot.repository.UserRepository;
import com.tutorial.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

  private ComponentDependency dependency;

  @Autowired
  private MyBean bean;

  @Autowired
  private MyBeanWithDependecy myBeanWithDependecy;

  @Autowired
  private MyBeanWithProperties myBeanWithProperties;

  @Autowired
  private UserPojo userPojo;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  public SpringbootApplication(@Qualifier("componentImplementTwo") ComponentDependency dependency) {
    this.dependency = dependency;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringbootApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
//    deprecatedExamples();
//    saveUsersInDb();
//    getInformationFromUser();
    saveWithErrorTransactional();
  }

  private void saveUsersInDb() {
    User john = new User("John", "john@email.com", LocalDate.of(1980, 03, 20));
    User julie = new User("Julie", "julie@email.com", LocalDate.of(1988, 06, 04));
    User daniel = new User("Jair", "jair@email.com", LocalDate.of(1987, 03, 03));

    List<User> users = Arrays.asList(john, julie, daniel);
    users.forEach(userRepository::save);
  }

  private void saveWithErrorTransactional() {
    User u1 = new User("Transactional1", "u1@email.com", LocalDate.now());
    User u2 = new User("Transactional2", "u2@email.com", LocalDate.now());
    User u3 = new User("Transactional3", "u3@email.com", LocalDate.now());
    User u4 = new User("Transactional4", "u4@email.com", LocalDate.now());

    List<User> users = Arrays.asList(u1, u2, u3 ,u4);

    try {
      userService.saveTransactional(users);
    } catch(Exception e) {
      log.error(e.getStackTrace().toString());
    }
    userService.getAllUsers()
        .stream()
        .forEach(user -> log.info(user.toString()));
  }

  private void getInformationFromUser() {
    log.info("User found: " +
        userRepository.findByUserEmail("john@email.com")
            .orElseThrow(() -> new RuntimeException("User Not Found")));
        userRepository.findAndSortByName("J", Sort.by("id").ascending())
            .stream()
            .forEach(user -> log.info("User sorted " + user));
        userRepository.findByName("Jair")
            .stream()
            .forEach(user -> log.info("User found by name " + user));
    log.info("User found by findByEmailAndName: " +
        userRepository.findByEmailAndName("julie@email.com", "Julie")
            .orElseThrow(() -> new RuntimeException("User Not Found")));

    userRepository.findByNameLike("%J%")
        .stream()
        .forEach(user -> log.info("User found by findByNameLike " + user));

    userRepository.findByNameOrEmail(null, "jair@email.com")
        .stream()
        .forEach(user -> log.info("User found by findByNameOrEmail " + user));

    userRepository.findByNameOrEmail("Julie", null)
        .stream()
        .forEach(user -> log.info("User found by findByNameOrEmail " + user));

    userRepository.findByNameAndEmail("Jair", "jair@email.com")
        .stream()
        .forEach(user -> log.info("User found by findByNameAndEmail " + user));

    userRepository.findByNameOrEmail("John", null)
        .stream()
        .forEach(user -> log.info("User by findByNameOrEmail " + user));
    userRepository.findByBirthDateBetween(LocalDate.of(1985, 01, 01), LocalDate.now())
        .stream()
        .forEach(user -> log.info("User found by findByBirthDateBetween " + user));

    userRepository.findByNameLikeOrderByIdDesc("%i%")
        .stream()
        .forEach(user -> log.info("User found by findByNameLikeOrderByIdDesc " + user));

    userRepository.findByNameContainingOrderByIdAsc("o")
        .stream()
        .forEach(user -> log.info("User found by findByNameContainingOrderByIdAsc " + user));

    log.info("User found by getAllByBirthDateAndEmail: " +
      userRepository.getAllByBirthDateAndEmail(LocalDate.of(1987, 03, 03), "jair@email.com")
          .orElseThrow(() -> new RuntimeException("User Not Found"))
    );
  }

  private void deprecatedExamples() {
    dependency.greet();
    bean.print();
    myBeanWithDependecy.printWithDependency();
    System.out.println(myBeanWithProperties.fun());
    System.out.println(userPojo.getEmail() + " " + userPojo.getPassword() + " " + userPojo.getAge());
    log.info("Finish run");
  }
}
