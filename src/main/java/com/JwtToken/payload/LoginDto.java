package com.JwtToken.payload;

import lombok.Data;

@Data
public class LoginDto {                      // when user provides username and password it will come to this Dto from client

    private String usernameOrEmail;
    private String password;
}
