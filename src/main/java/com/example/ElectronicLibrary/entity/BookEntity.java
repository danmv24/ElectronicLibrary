package com.example.ElectronicLibrary.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "books")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @Column(nullable = false)
    private String description;

}
