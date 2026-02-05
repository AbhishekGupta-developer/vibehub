package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.request.UserRequestDto;
import com.myorganisation.vibehub.dto.response.GenericResponseDto;
import com.myorganisation.vibehub.dto.response.UserResponseDto;
import com.myorganisation.vibehub.model.User;
import com.myorganisation.vibehub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = mapUserRequestDtoToUser(userRequestDto, new User());
        userRepository.addUser(user);
        return mapUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        return mapUserToUserResponseDto(userRepository.getUser(id));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.getAllUsers();
        List<UserResponseDto> userResponseDtoList = new LinkedList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.getUser(id);
        mapUserRequestDtoToUser(userRequestDto, user);
        userRepository.updateUser(user);

        return mapUserToUserResponseDto(user);
    }

    @Override
    public GenericResponseDto removeUser(Long id) {
        User user = userRepository.getUser(id);
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        if(user != null) {
            String name = user.getName();
            userRepository.removeUser(id);
            genericResponseDto.setIsSuccess(true);
            genericResponseDto.setMessage("User name: " + name + " removed successfully");
        } else {
            genericResponseDto.setIsSuccess(false);
            genericResponseDto.setMessage("User id: " + id + " doesn't exist");
        }

        return genericResponseDto;
    }

    // Helper methods
    // Map User to UserResponseDto
    private UserResponseDto mapUserToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setGender(user.getGender());

        return userResponseDto;
    }

    // Map UserRequestDto to User
    private User mapUserRequestDtoToUser(UserRequestDto userRequestDto, User user) {
        user.setName(userRequestDto.getName());
        user.setUserName(userRequestDto.getUserName());
        user.setPassword(userRequestDto.getPassword());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        user.setGender(userRequestDto.getGender());

        return user;
    }
}
