package com.example.ElectronicLibrary.entity;


import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @NonNull
    private String roleName;
}
