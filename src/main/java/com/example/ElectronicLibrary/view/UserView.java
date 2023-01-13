package com.example.ElectronicLibrary.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserView {

    public Long id;

    public String username;

    public String role;
}
