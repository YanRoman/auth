package com.post.post.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

}
