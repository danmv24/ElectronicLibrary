package com.example.ElectronicLibrary.entity;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL)
    private List<BookEntity> books = new ArrayList<>();

}
