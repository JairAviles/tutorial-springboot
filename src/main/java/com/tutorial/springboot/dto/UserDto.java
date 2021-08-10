package com.tutorial.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
  private Long id;
  private String name;
  private LocalDate birthDate;
}
