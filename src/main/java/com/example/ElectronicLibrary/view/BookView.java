package com.example.ElectronicLibrary.view;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class BookView {

    private Long id;

    private String title;

    private String authorName;

    private String authorSurname;

    private String description;

//    private String link;
}