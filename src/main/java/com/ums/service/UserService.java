package com.ums.service;

import com.ums.payload.LoginDto;
import com.ums.payload.UserDto;

public interface UserService {
    UserDto addUser(UserDto dto);
    String verifyLogin(LoginDto dto);
}
