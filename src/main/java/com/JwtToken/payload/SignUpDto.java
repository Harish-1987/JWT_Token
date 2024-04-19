package com.JwtToken.payload;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}



//Its a POJO class -> a class which is dealing with getters and setters