package com.example.ElectronicLibrary.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserForm {

    private String username;

    private String password;
}
