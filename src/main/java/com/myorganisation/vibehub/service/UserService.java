package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.request.UserRequestDto;
import com.myorganisation.vibehub.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto);
}
