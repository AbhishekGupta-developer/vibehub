package com.myorganisation.vibehub.controller;

import com.myorganisation.vibehub.dto.response.ServerResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ServerController {

    @GetMapping
    public ResponseEntity<ServerResponseDto> serverStatus() {
        return new ResponseEntity<>(new ServerResponseDto(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/test")
    public String testApi() {
        return "TEST API - GET";
    }

    @PostMapping
    public String testApiPost() {
        return "TEST API - POST";
    }
}
