package com.example.ElectronicLibrary.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookForm {

    private String title;

    private String authorName;

    private String authorSurname;

    private String description;

}
