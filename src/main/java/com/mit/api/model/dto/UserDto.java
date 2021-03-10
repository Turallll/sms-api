package com.mit.api.model.dto;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String surname;
    private String email;
    private String mobile;
    private String identifier;
}
