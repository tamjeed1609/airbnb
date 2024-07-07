package com.ums.payload;

import com.ums.entity.AppUser;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserTransformer {

   public static AppUser userDtoToUser (UserDto dto){
       AppUser user = AppUser.builder()
               .name(dto.getName())
               .username(dto.getUsername())
               .password(BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt(10)))
               .emailId(dto.getEmailId())
               .userRole(dto.getUserRole())
               .build();

       return user;

   }

    public static UserDto userToUserDto (AppUser user){
        UserDto dto = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .emailId(user.getEmailId())
                .userRole(user.getUserRole())
                .build();

        return dto;

    }


}