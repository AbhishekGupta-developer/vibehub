package com.myorganisation.vibehub.dto.response;

import com.myorganisation.vibehub.enums.Gender;
import com.myorganisation.vibehub.enums.UserRole;
import com.myorganisation.vibehub.model.ProfilePicture;
import com.myorganisation.vibehub.model.Wallet;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String phone;
    private Gender gender;
    private UserRole role;
    private Wallet wallet;
    private ProfilePicture profilePicture;
}
