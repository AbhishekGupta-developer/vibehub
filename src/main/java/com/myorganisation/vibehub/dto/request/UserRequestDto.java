package com.myorganisation.vibehub.dto.request;

import com.myorganisation.vibehub.enums.Gender;
import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private Gender gender;
}
