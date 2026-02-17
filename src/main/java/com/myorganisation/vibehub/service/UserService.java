package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.request.UserRequestDto;
import com.myorganisation.vibehub.dto.response.GenericResponseDto;
import com.myorganisation.vibehub.dto.response.UserResponseDto;
import com.myorganisation.vibehub.enums.Gender;

import java.util.List;

public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto);
    UserResponseDto getUser(Long id);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    GenericResponseDto removeUser(Long id);

    // Custom finder methods
    UserResponseDto searchByUserName(String name);
    List<UserResponseDto> searchByName(String name);
    List<UserResponseDto> searchByNameAndGender(String name, Gender gender);
}
