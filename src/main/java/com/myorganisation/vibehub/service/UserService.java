package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.request.LoginRequestDto;
import com.myorganisation.vibehub.dto.request.ProfilePictureRequestDto;
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

    UserResponseDto searchByUserName(String name);
    List<UserResponseDto> searchByName(String name);
    List<UserResponseDto> searchByNameAndGender(String name, Gender gender);

    List<UserResponseDto> searchUsersByEmailDomain(String emailDomain);

    UserResponseDto searchUserByIdUsingNativeQuery(Long id);
    List<UserResponseDto> searchUsersByGenderUsingNativeQuery(Gender gender);

    GenericResponseDto uploadProfilePicture(Long id, ProfilePictureRequestDto profilePictureRequestDto);

    GenericResponseDto login(LoginRequestDto loginRequestDto);
}
