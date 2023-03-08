package com.cops.controller;

import com.cops.db.EmployeeDB;
import com.cops.model.EmployeeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeDB employeeDB;

    @GetMapping("/getAllEmployees")
    public List<EmployeeModel> getAllEmployees() {
        log.info("EmployeeController::getAllEmployees");
        return employeeDB.findAll();
    }

    @PostMapping("/addEmployee")
    public EmployeeModel addEmployee(@RequestBody EmployeeModel employeeModel) {
        log.info("EmployeeController::addEmployee");
        return employeeDB.save(employeeModel);
    }
}
