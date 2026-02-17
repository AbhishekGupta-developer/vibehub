package com.myorganisation.vibehub.controller;

import com.myorganisation.vibehub.dto.request.UserRequestDto;
import com.myorganisation.vibehub.dto.response.GenericResponseDto;
import com.myorganisation.vibehub.dto.response.UserResponseDto;
import com.myorganisation.vibehub.enums.Gender;
import com.myorganisation.vibehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.addUser(userRequestDto), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.updateUser(id, userRequestDto), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> removeUser(@RequestParam Long id) {
        return new ResponseEntity<>(userService.removeUser(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search/username/{username}")
    public ResponseEntity<UserResponseDto> searchByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.searchByUserName(username), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<UserResponseDto>> searchByName(@PathVariable String name) {
        return new ResponseEntity<>(userService.searchByName(name), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search/params")
    public ResponseEntity<List<UserResponseDto>> searchByNameAndGender(@RequestParam String name, @RequestParam Gender gender) {
        return new ResponseEntity<>(userService.searchByNameAndGender(name,gender), HttpStatusCode.valueOf(200));
    }
}
