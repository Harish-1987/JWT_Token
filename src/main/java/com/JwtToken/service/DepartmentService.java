package com.JwtToken.service;

import com.JwtToken.payload.DepartmentDto;
import com.JwtToken.payload.DepartmentResponse;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long departmentId);

    DepartmentResponse getAllDepartment(int pageNo, int pageSize, String sortBy, String sortDir);

    DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepdto);

    void deleteDepartment(Long departmentId);

}
