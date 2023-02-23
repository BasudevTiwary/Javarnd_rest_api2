package com.employee.service.impl;

import com.employee.entity.Employee;
import com.employee.payload.EmployeeDto;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;
    @Override
    public List<EmployeeDto> getAllRecord() {

        List<Employee>  listEmployee = employeeRepo.findAll();
        List<EmployeeDto> listEmployeeDto = listEmployee.stream().map(x -> mapToDto(x)).collect(Collectors.toList());

        return listEmployeeDto;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Employee save = employeeRepo.save(employee);
        EmployeeDto employeeDto1 = mapToDto(save);

        return employeeDto1;
    }

    @Override
    public EmployeeDto updateOneRecord(EmployeeDto employeeDto, long id) {
        Optional<Employee> byId = employeeRepo.findById(id);
        Employee employee = byId.get();
        employee.setId(id);
        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setDesignation(employeeDto.getDesignation());

        employeeRepo.save(employee);
        EmployeeDto employeeDto1 = mapToDto(employee);
        return employeeDto1;
    }

    @Override
    public void deleteOneRecord(long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public List<EmployeeDto> getByDesignation(String designation) {
        List<Employee> byDesignation = employeeRepo.findByDesignation(designation);
        List<EmployeeDto> collect = byDesignation.stream().map(x -> mapToDto(x)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public EmployeeDto getByName(String name) {

        Optional<Employee> byName = employeeRepo.findByName(name);
        Employee employee = byName.get();
        EmployeeDto employeeDto = mapToDto(employee);
        return employeeDto;
    }


    public  EmployeeDto mapToDto(Employee employee){

        EmployeeDto employeeDto= new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setDesignation(employee.getDesignation());

        return employeeDto;
    }
    public  Employee mapToEntity (EmployeeDto employeeDto){

        Employee employee= new Employee();

       employee.setId(employeeDto.getId());
       employee.setName(employeeDto.getName());
       employee.setDepartment(employeeDto.getDepartment());
       employee.setDesignation(employeeDto.getDesignation());

        return employee;
    }
}
