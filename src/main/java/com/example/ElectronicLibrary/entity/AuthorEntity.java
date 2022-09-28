package com.example.ElectronicLibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "authors")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    @OneToMany(mappedBy = "author")
    private List<BookEntity> books = new ArrayList<>();

}
