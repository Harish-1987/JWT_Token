package com.JwtToken.service.impl;

import com.JwtToken.entity.Department;
import com.JwtToken.entity.Employee;
import com.JwtToken.exception.ResourceNotFoundException;
import com.JwtToken.payload.EmployeeDto;
import com.JwtToken.repository.DepartmentRepository;
import com.JwtToken.repository.EmployeeRepository;
import com.JwtToken.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ModelMapper mapper;



    @Override
    public EmployeeDto createEmployee(long departmentId, EmployeeDto employeeDto){
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Record is not present with given id: " + departmentId));

        Employee employee= mapToEmployee(employeeDto);
        employee.setDepartment(department);
        Employee newemployee = employeeRepository.save(employee);

        return mapToEmployeeDto(newemployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new  ResourceNotFoundException("Record is not present with given id: "+employeeId));
        return mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> collect = employees.stream().map(emp -> mapToEmployeeDto(emp)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeedto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Record is not present with Id:"+employeeId));

        employee.setName(updatedEmployeedto.getName());
        employee.setCity(updatedEmployeedto.getCity());
        employee.setDept(updatedEmployeedto.getDept());

        Employee savedEmployee = employeeRepository.save(employee);
        return mapToEmployeeDto(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Record is not present with id:" + employeeId));

        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeDto> findByDepartmentId(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Record is not present with id:" + departmentId));

        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees.stream().map(employee->mapToEmployeeDto(employee)).collect(Collectors.toList());
    }


    Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee = mapper.map(employeeDto, Employee.class);
        return employee;
    }

    EmployeeDto mapToEmployeeDto(Employee employee){
        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

}
