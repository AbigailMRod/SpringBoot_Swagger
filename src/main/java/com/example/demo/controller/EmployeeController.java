package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.EmployeeService;
import com.example.demo.model.Employee;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/employee")
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }//constructor

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public List<Employee> getEmployees() {
            return employeeService.getEmployees();
    } // getEmployees

    // api/employee/1
    @ApiOperation(value = "Get an Employee by Id")
    @GetMapping(path = "{employeeId}")
    public Employee getEmployee(@PathVariable ("employeeId")
            Long employeeId ) {
        return employeeService.getEmployee(employeeId);
    } // getEmployee

    // api/employee/1
    @ApiOperation(value = "Delete an employee")
    @DeleteMapping(path = "{employeeId}")
    public void deleteEmployee(@PathVariable ("employeeId")
                                        Long employeeId ) {
        employeeService.deleteEmployee(employeeId);
    } // deleteEmployee
    
    @ApiOperation(value = "Add an employee")
    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    } //addEmployee
    
    @ApiOperation(value = "Update an employee")
    @PutMapping (path = "{employeeId}")
    public void updateEmployee (@PathVariable ("employeeId")Long employeeId,
                                   @RequestParam(required=false) String lastName,
                               @RequestParam(required=false) int age,
                               @RequestParam(required=false) String rfc) {
        employeeService.updateEmployee(employeeId, lastName, age, rfc);
    } //updateEmployee

}//class EmployeeController
