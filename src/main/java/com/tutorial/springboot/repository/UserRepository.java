package com.tutorial.springboot.repository;

import com.tutorial.springboot.dto.UserDto;
import com.tutorial.springboot.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
  @Query("select u from User u WHERE u.email=?1")
  Optional<User> findByUserEmail(String email);

  @Query("select u from User u WHERE u.name like ?1%")
  List<User> findAndSortByName(String name, Sort sort);

  List<User> findByName(String name);

  Optional<User> findByEmailAndName(String email, String name);

  List<User> findAll();

  List<User> findByNameLike(String name);

  List<User> findByNameOrEmail(String name, String email);

  List<User> findByNameAndEmail(String name, String email);

  List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

  List<User> findByNameLikeOrderByIdDesc(String name);

  List<User> findByNameContainingOrderByIdAsc(String name);

  @Query("select new com.tutorial.springboot.dto.UserDto(u.id, u.name, u.birthDate)" +
          " FROM User u " +
          " WHERE u.birthDate =:birthDateParam" +
          " AND u.email =:emailParam")
  Optional<UserDto> getAllByBirthDateAndEmail(@Param("birthDateParam") LocalDate birthDateParam,
                                              @Param("emailParam")String email);
}
