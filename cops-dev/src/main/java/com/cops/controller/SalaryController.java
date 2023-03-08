package com.cops.controller;

import com.cops.db.SalaryDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    SalaryDB salaryDB;

//    @PostMapping("/addDepartment")
//    public DepartmentModel addDepartment(@RequestBody DepartmentModel departmentModel) {
//        log.info("DepartmentController::addDepartment");
//        return salaryDB.save(departmentModel);
//    }
}
