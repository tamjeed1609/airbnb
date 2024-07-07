package com.ums.controller;

import com.ums.entity.AppUser;
import com.ums.payload.JwtResponse;
import com.ums.payload.LoginDto;
import com.ums.payload.UserDto;
import com.ums.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtResponse jwtResponse;

    @PostMapping("/{addUser}")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto dto, BindingResult br) {
        if(br.hasErrors()){
            return new ResponseEntity<>(br.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserDto userDto = service.addUser(dto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        String token = service.verifyLogin(dto);
        if (token != null) {
            jwtResponse.setToken(token);
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/profile")
    public AppUser getCurrentProfile(@AuthenticationPrincipal AppUser user) {
        return user;
    }
}