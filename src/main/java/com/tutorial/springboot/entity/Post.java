package com.tutorial.springboot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NonNull
  private String description;

  @ManyToOne
  @NonNull
  private User user;

}
