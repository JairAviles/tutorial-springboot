package com.tutorial.springboot.repository;

import com.tutorial.springboot.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("select u from User u WHERE u.email=?1")
  Optional<User> findByUserEmail(String email);

  @Query("select u from User u WHERE u.name like ?1%")
  List<User> findAndSortByName(String name, Sort sort);

  List<User> findByName(String name);

  Optional<User> findByEmailAndName(String email, String name);

  List<User> findByNameLike(String name);

  List<User> findByNameOrEmail(String name, String email);

  List<User> findByNameAndEmail(String name, String email);

  List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

  List<User> findByNameLikeOrderByIdDesc(String name);

  List<User> findByNameContainingOrderByIdAsc(String name);
}
