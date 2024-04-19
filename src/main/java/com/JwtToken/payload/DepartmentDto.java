package com.JwtToken.payload;

import lombok.Data;

@Data
public class DepartmentDto {
    private Long id;
    private String depname;
    private String depcode;
}
