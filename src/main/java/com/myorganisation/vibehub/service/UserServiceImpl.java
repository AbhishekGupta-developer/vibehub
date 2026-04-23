package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.request.LoginRequestDto;
import com.myorganisation.vibehub.dto.request.ProfilePictureRequestDto;
import com.myorganisation.vibehub.dto.request.UserRequestDto;
import com.myorganisation.vibehub.dto.response.GenericResponseDto;
import com.myorganisation.vibehub.dto.response.UserResponseDto;
import com.myorganisation.vibehub.enums.Gender;
import com.myorganisation.vibehub.exception.UserNotFoundException;
import com.myorganisation.vibehub.model.*;
import com.myorganisation.vibehub.repository.CountryRepository;
import com.myorganisation.vibehub.repository.NumberOfUserRepository;
import com.myorganisation.vibehub.repository.ProfilePictureRepository;
import com.myorganisation.vibehub.repository.UserRepository;
import com.myorganisation.vibehub.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfilePictureRepository profilePictureRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private NumberOfUserRepository numberOfUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        NumberOfUser numberOfUser = numberOfUserRepository.findById(1L).orElse(null);
        numberOfUser.setCounter(numberOfUser.getCounter() + 1);
        numberOfUserRepository.save(numberOfUser);

        User user = mapUserRequestDtoToUser(userRequestDto, new User());

        Wallet wallet = new Wallet();
        wallet.setBalance(0D);
        wallet.setUser(user);

        user.setWallet(wallet);

        Country country = countryRepository.findById(userRequestDto.getCountryId()).orElse(null);
        user.setCountry(country);

        userRepository.save(user);

        return mapUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id: " + id + " doesn't exist"));
        return mapUserToUserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new LinkedList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElse(null);
        mapUserRequestDtoToUser(userRequestDto, user);
        userRepository.save(user);

        return mapUserToUserResponseDto(user);
    }

    @Override
    public GenericResponseDto removeUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        if(user != null) {
            String name = user.getName();
            userRepository.deleteById(id);
            genericResponseDto.setIsSuccess(true);
            genericResponseDto.setMessage("User name: " + name + " removed successfully");
        } else {
            genericResponseDto.setIsSuccess(false);
            genericResponseDto.setMessage("User id: " + id + " doesn't exist");
        }

        return genericResponseDto;
    }

    @Override
    public UserResponseDto searchByUserName(String name) {
        return mapUserToUserResponseDto(userRepository.findByUserName(name).orElse(null));
    }

    @Override
    public List<UserResponseDto> searchByName(String name) {
        List<User> userList = userRepository.findByNameContaining(name);
        List<UserResponseDto> userResponseDtoList = new LinkedList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public List<UserResponseDto> searchByNameAndGender(String name, Gender gender) {
        List<User> userList = userRepository.searchUserByExactNameAndGender(name, gender);
        List<UserResponseDto> userResponseDtoList = new LinkedList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public List<UserResponseDto> searchUsersByEmailDomain(String emailDomain) {
        List<User> userList = userRepository.searchUserByEmailDomain('@' + emailDomain);
        List<UserResponseDto> userResponseDtoList = new LinkedList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public UserResponseDto searchUserByIdUsingNativeQuery(Long id) {
        User user = userRepository.searchUserByIdUsingNativeQuery(id).orElse(null);
        return mapUserToUserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> searchUsersByGenderUsingNativeQuery(Gender gender) {
        List<User> userList = userRepository.searchUsersByGenderUsingNativeQuery(gender.name());
        List<UserResponseDto> userResponseDtoList = new LinkedList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public GenericResponseDto uploadProfilePicture(Long id, ProfilePictureRequestDto profilePictureRequestDto) {
        User user = userRepository.findById(id).orElse(null);

        ProfilePicture profilePicture = new ProfilePicture();
        profilePicture.setUrl(profilePictureRequestDto.getUrl());
        profilePicture.setAlternativeText(user.getName() + "'s profile picture not found");
        profilePicture.setUser(user);

        profilePictureRepository.save(profilePicture);

        user.setProfilePicture(profilePicture);
        userRepository.save(user); // update -> profile picture id

        GenericResponseDto genericResponseDto = new GenericResponseDto();
        genericResponseDto.setIsSuccess(true);
        genericResponseDto.setMessage("Profile picture uploaded successfully");
        genericResponseDto.setDetails(Map.of("profileId", user.getProfilePicture().getId()));

        return genericResponseDto;
    }

    @Override
    public GenericResponseDto login(LoginRequestDto loginRequestDto) {
        User user = (User) userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        String password = user.getPassword();
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        if(passwordEncoder.matches(loginRequestDto.getPassword(), password)) {
            String token = jwtUtil.generateToken(user);
            genericResponseDto.setIsSuccess(true);
            genericResponseDto.setMessage("User authenticated");
            genericResponseDto.setDetails(Map.of("Access token", token));
        } else {
            genericResponseDto.setIsSuccess(false);
            genericResponseDto.setMessage("User authentication failed");
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
        userResponseDto.setRole(user.getRole());
        userResponseDto.setWallet(user.getWallet());
        userResponseDto.setProfilePicture(user.getProfilePicture());

        return userResponseDto;
    }

    // Map UserRequestDto to User
    private User mapUserRequestDtoToUser(UserRequestDto userRequestDto, User user) {
        user.setName(userRequestDto.getName());
        user.setUserName(userRequestDto.getUserName());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        user.setGender(userRequestDto.getGender());
        user.setRole(userRequestDto.getRole());

        return user;
    }
}
