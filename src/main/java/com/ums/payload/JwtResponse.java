package com.ums.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtResponse {
    private String type="Bearer";
    private String token;
}
