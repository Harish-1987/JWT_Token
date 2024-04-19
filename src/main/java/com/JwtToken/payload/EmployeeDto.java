package com.JwtToken.payload;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String city;
    private String dept;
}
