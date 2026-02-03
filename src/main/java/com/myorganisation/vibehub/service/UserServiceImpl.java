package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.request.UserRequestDto;
import com.myorganisation.vibehub.dto.response.UserResponseDto;
import com.myorganisation.vibehub.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
    }
}
