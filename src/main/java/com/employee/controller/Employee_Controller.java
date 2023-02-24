package com.employee.controller;

import com.employee.payload.EmployeeDto;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("empl/api")
public class Employee_Controller {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> postEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeService.saveEmployee(employeeDto);
        ResponseEntity<EmployeeDto> employeeDtoResponseEntity = new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
        return employeeDtoResponseEntity;
    }
    @GetMapping
    public List<EmployeeDto> getAllEmployee(){

        List<EmployeeDto> allRecord = employeeService.getAllRecord();

        return allRecord;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateRecord(@RequestBody EmployeeDto employeeDto,@PathVariable("id") long id){

        EmployeeDto employeeDto1 = employeeService.updateOneRecord(employeeDto, id);
        ResponseEntity<EmployeeDto> employeeDtoResponseEntity = new ResponseEntity<>(employeeDto1, HttpStatus.OK);
        return  employeeDtoResponseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedData(@PathVariable("id") long id){

        employeeService.deleteOneRecord(id);

        ResponseEntity<String> dataHasBeenDeleted = new ResponseEntity<>("data has been deleted", HttpStatus.OK);

        return dataHasBeenDeleted;
    }

    @GetMapping("/designation/{designation}")
    public List<EmployeeDto> getByDesignation(@PathVariable("designation") String designation){

        List<EmployeeDto> byDesignation = employeeService.getByDesignation(designation);
//        ResponseEntity<EmployeeDto> employeeDtoResponseEntity = new ResponseEntity<>(byDesignation, HttpStatus.OK);

        return byDesignation;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EmployeeDto> getByName(@PathVariable("name") String name){
        EmployeeDto byName = employeeService.getByName(name);
        ResponseEntity<EmployeeDto> employeeDtoResponseEntity = new ResponseEntity<>(byName, HttpStatus.OK);
        return  employeeDtoResponseEntity;

    }

}
