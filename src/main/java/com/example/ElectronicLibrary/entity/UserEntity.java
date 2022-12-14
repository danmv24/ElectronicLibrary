package com.example.ElectronicLibrary.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "username", unique = true)
    private String username;

    @NonNull
    @Column(name = "password")
    private String password;

    private String role;

}
