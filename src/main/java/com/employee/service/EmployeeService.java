package com.employee.service;


import com.employee.payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllRecord();

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto updateOneRecord(EmployeeDto employeeDto, long id);

    void deleteOneRecord(long id);

    List<EmployeeDto> getByDesignation(String designation);

    EmployeeDto getByName(String name);
}
