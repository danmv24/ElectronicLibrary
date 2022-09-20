package com.example.ElectronicLibrary.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "author")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
