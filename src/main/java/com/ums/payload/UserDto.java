package com.ums.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    @NotEmpty
    @Size(min=2,message="Name should contain atleast 2 characters")
    private String name;
    private String username;
    private String password;
    @Email
    private String emailId;
    private String userRole;
}
