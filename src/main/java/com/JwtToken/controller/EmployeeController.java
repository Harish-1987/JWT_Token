package com.JwtToken.controller;
import com.JwtToken.payload.EmployeeDto;
import com.JwtToken.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Emp")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @PostMapping("/{id}/save")
    public ResponseEntity<EmployeeDto> createEmployee(@PathVariable("id") Long departmentId, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedemployee = employeeService.createEmployee(departmentId,employeeDto);
        return new ResponseEntity<>(savedemployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeedto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeedto);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployeedto) {
        EmployeeDto saveddto = employeeService.updateEmployee(employeeId, updatedEmployeedto);
        return ResponseEntity.ok(saveddto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Record is deleted Successfully");
    }

    //http://localhost:8080/api/Emp/dep/1
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dep/{id}")
    public ResponseEntity<List<EmployeeDto>> findByDepartmentId(@PathVariable("id") Long departmentId){
        List<EmployeeDto> employees = employeeService.findByDepartmentId(departmentId);
        return ResponseEntity.ok(employees);
    }


}