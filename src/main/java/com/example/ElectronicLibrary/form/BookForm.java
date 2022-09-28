package com.example.ElectronicLibrary.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookForm {

    private Long id;

    private String title;

    private String authorName;

    private String authorSurname;

    private String description;

    private byte[] data;
}
