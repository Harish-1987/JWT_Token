package com.JwtToken.service.impl;

import com.JwtToken.entity.Department;
import com.JwtToken.exception.ResourceNotFoundException;
import com.JwtToken.payload.DepartmentDto;
import com.JwtToken.payload.DepartmentResponse;
import com.JwtToken.repository.DepartmentRepository;
import com.JwtToken.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto){
        Department department = mapToDepartment(departmentDto);
        Department saveddepartment =  departmentRepository.save(department);
        return mapToDepartmentDto(saveddepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId){
        Department department =  departmentRepository.findById(departmentId)
                .orElseThrow(()->new ResourceNotFoundException("Resource is not present with id:"+departmentId));
        return mapToDepartmentDto(department);
    }

//    @Override
//    public List<DepartmentDto> getAllDepartment(){
//        List<Department> departments = departmentRepository.findAll();
//        List<DepartmentDto> allDepdto = departments.stream().map(dep->mapToDepartmentDto(dep)).collect(Collectors.toList());
//        return allDepdto;
//    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartmentDto){
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()->new ResourceNotFoundException("Record is not present with given id: "+departmentId));

        department.setDepname(updatedDepartmentDto.getDepname());
        department.setDepcode(updatedDepartmentDto.getDepcode());

        Department dep = departmentRepository.save(department);
        return mapToDepartmentDto(dep);
    }

    @Override
    public void deleteDepartment(Long departmentId){
        Department dep = departmentRepository.findById(departmentId)
                .orElseThrow(()->new ResourceNotFoundException("Record is not present with id: "+departmentId));

        departmentRepository.delete(dep);
    }

    @Override
    public DepartmentResponse getAllDepartment(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Department> pageDepartments = departmentRepository.findAll(pageable);

        List<Department> departments = pageDepartments.getContent();
        List<DepartmentDto> departmentDtos = departments.stream().map(department->mapToDepartmentDto(department))
                .collect(Collectors.toList());

        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartmentDto(departmentDtos);
        departmentResponse.setPageNo(pageDepartments.getNumber());
        departmentResponse.setPageSize(pageDepartments.getSize());
        departmentResponse.setTotalElements(pageDepartments.getTotalElements());
        departmentResponse.setLast(pageDepartments.isLast());
        departmentResponse.setTotalPages(pageDepartments.getTotalPages());

        return departmentResponse;
    }




    public DepartmentDto mapToDepartmentDto(Department department){
        DepartmentDto dto =  mapper.map(department, DepartmentDto.class);
        return dto;
    }

    public Department mapToDepartment(DepartmentDto departmentDto){
        Department department = mapper.map(departmentDto, Department.class);
        return department;
    }

}
