package com.myorganisation.vibehub.controller;

import com.myorganisation.vibehub.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/jwt")
public class TestJwtTokenController {
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public String generateToken(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }
}
