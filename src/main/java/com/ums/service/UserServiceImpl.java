package com.ums.service;

import com.ums.entity.AppUser;
import com.ums.payload.LoginDto;
import com.ums.payload.UserDto;
import com.ums.payload.UserTransformer;
import com.ums.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private JWTService jwtService;
    @Override
    public UserDto addUser(UserDto dto) {
        AppUser appUser = UserTransformer.userDtoToUser(dto);
        AppUser save = repository.save(appUser);
        UserDto userDto = UserTransformer.userToUserDto(save);
        return userDto;
    }

    @Override
    public String verifyLogin(LoginDto dto) {
        Optional<AppUser> byUsername = repository.findByUsername(dto.getUsername());
        if(byUsername.isPresent()){
            AppUser appUser = byUsername.get();
            if(BCrypt.checkpw(dto.getPassword(),appUser.getPassword())){
            return jwtService.generateToken(appUser);
            }
        }
            return null;
    }
/*
    AppUser mapToEntity(UserDto dto){
        AppUser user=new AppUser();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt(10)));
        user.setEmailId(dto.getEmailId());
        user.setUserRole(dto.getUserRole());
        return user;
   }

   UserDto mapToDto(AppUser user){
        UserDto dto=new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmailId(user.getEmailId());
        dto.setUserRole(user.getUserRole());
        return dto;
   }
*/
}
