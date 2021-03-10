package com.mit.api.model.dto;

import lombok.Data;

@Data
public class UserInfoDto {

    private String name;
    private String surname;
    private String email;
    private String mobile;
    private ImageDto image;
}
