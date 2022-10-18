package com.example.ElectronicLibrary.view;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookView {

    private Long id;

    private String title;

    private String authorName;

    private String authorSurname;

    private String description;
}