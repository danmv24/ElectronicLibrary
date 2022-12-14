package com.example.ElectronicLibrary.entity;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity(name = "books")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String title;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @Column(nullable = false)
    @NonNull
    private String description;

}
