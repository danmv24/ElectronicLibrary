package com.example.ElectronicLibrary.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
