package com.JwtToken.service;

import com.JwtToken.payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(long departmentId, EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeedto);
    void deleteEmployee(Long employeeId);

    List<EmployeeDto> findByDepartmentId(Long departmentId);
}
