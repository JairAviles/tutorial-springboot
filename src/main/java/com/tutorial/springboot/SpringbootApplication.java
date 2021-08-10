package com.tutorial.springboot;

import com.tutorial.springboot.bean.MyBean;
import com.tutorial.springboot.bean.MyBeanWithDependecy;
import com.tutorial.springboot.bean.MyBeanWithProperties;
import com.tutorial.springboot.component.ComponentDependency;
import com.tutorial.springboot.entity.User;
import com.tutorial.springboot.pojo.UserPojo;
import com.tutorial.springboot.repository.UserRepository;
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

  public SpringbootApplication(@Qualifier("componentImplementTwo") ComponentDependency dependency) {
    this.dependency = dependency;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringbootApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
//    deprecatedExamples();
    saveUsersInDb();
    getInformationFromUser();
  }

  private void saveUsersInDb() {
    User john = new User("John", "john@email.com", LocalDate.of(1980, 03, 20));
    User julie = new User("Julie", "julie@email.com", LocalDate.of(1988, 06, 04));
    User daniel = new User("Jair", "jair@email.com", LocalDate.of(1987, 03, 03));

    List<User> users = Arrays.asList(john, julie, daniel);
    users.forEach(userRepository::save);
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
            .forEach(user -> log.info("User by name " + user));
    log.info("User found by findByEmailAndName: " +
        userRepository.findByEmailAndName("julie@email.com", "Julie")
            .orElseThrow(() -> new RuntimeException("User Not Found")));
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
