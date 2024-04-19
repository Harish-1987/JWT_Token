package com.JwtToken.controller;

import com.JwtToken.payload.DepartmentDto;
import com.JwtToken.payload.DepartmentResponse;
import com.JwtToken.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Dep")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentdto){
        DepartmentDto saveddep = departmentService.createDepartment(departmentdto);
        return new ResponseEntity<>(saveddep, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){
        DepartmentDto department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<DepartmentDto>> getAllDepartment(){
//        List<DepartmentDto> allDepdto = departmentService.getAllDepartment();
//        return ResponseEntity.ok(allDepdto);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentDto updatedDepartmentdto){
        DepartmentDto saveddepartment = departmentService.updateDepartment(departmentId,updatedDepartmentdto);
        return ResponseEntity.ok(saveddepartment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Record deleted successfully.");
    }


    //http://localhost:8080/api/Dep/all?pageNo=1&pageSize=3&sortBy=depcode&sortDir=desc
    @GetMapping("/all")
    public DepartmentResponse getAllDepartments(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        DepartmentResponse departmentResponse = departmentService.getAllDepartment(pageNo, pageSize, sortBy, sortDir);            //Supply it to Service Layer
         return departmentResponse;
    }
}
