package com.tutorial.springboot;

import com.tutorial.springboot.bean.MyBean;
import com.tutorial.springboot.bean.MyBeanWithDependecy;
import com.tutorial.springboot.bean.MyBeanWithProperties;
import com.tutorial.springboot.component.ComponentDependency;
import com.tutorial.springboot.pojo.UserPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

  public SpringbootApplication(@Qualifier("componentImplementTwo") ComponentDependency dependency) {
    this.dependency = dependency;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringbootApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    dependency.greet();
    bean.print();
    myBeanWithDependecy.printWithDependency();
    System.out.println(myBeanWithProperties.fun());
    System.out.println(userPojo.getEmail() + " " + userPojo.getPassword() + " " + userPojo.getAge());
    log.info("Finish run");
  }
}
