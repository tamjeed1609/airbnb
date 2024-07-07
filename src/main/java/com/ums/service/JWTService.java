package com.ums.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ums.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    public static final String USER_NAME="name";
    @Value("${jwt.algorithmKey}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiryDuration}")
    private int expiryDuration;

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct(){
        algorithm=Algorithm.HMAC256(algorithmKey);
        System.out.println(issuer);
    }

    public String generateToken(AppUser user){
        return JWT.create().withClaim(USER_NAME,user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryDuration))
                .withIssuer(issuer)
                .sign(algorithm);

    }

    public String getUserName(String token){
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);

        return decodedJWT.getClaim(USER_NAME).asString();
    }

}
