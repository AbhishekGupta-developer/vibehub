package com.myorganisation.vibehub.model;

import com.myorganisation.vibehub.enums.Gender;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private Gender gender;
}
