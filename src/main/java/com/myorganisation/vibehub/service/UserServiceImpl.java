package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.request.UserRequestDto;
import com.myorganisation.vibehub.dto.response.UserResponseDto;
import com.myorganisation.vibehub.model.User;
import com.myorganisation.vibehub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setUserName(userRequestDto.getUserName());
        user.setPassword(userRequestDto.getPassword());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        user.setGender(userRequestDto.getGender());

        User storedUser = userRepository.addUser(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(storedUser.getId());
        userResponseDto.setName(storedUser.getName());
        userResponseDto.setUserName(storedUser.getUserName());
        userResponseDto.setEmail(storedUser.getEmail());
        userResponseDto.setPhone(storedUser.getPhone());
        userResponseDto.setGender(storedUser.getGender());

        return userResponseDto;
    }
}
