package com.example.ElectronicLibrary.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorForm {

    private Long id;

    private String name;

    private String surname;

}
