package com.cops.controller;

import com.cops.db.DepartmentDB;
import com.cops.model.DepartmentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentDB departmentDB;

    @GetMapping("/getDepartmentNameById/{id}")
    public String getDepartmentNameById(@PathVariable int id) {
        log.info("DepartmentController::getDepartmentNameById");
        return departmentDB.getReferenceById(id).getDepName();
    }

    @GetMapping("/getDepartmentName/{id}")
    public String getDepartmentLocationById(@PathVariable int id) {
        log.info("DepartmentController::getDepartmentLocationById");
        return departmentDB.getReferenceById(id).getDepLocation();
    }

    @GetMapping("/getAllDepartments")
    public List<DepartmentModel> getAllDepartments() {
        log.info("DepartmentController::getAllDepartments");
        return departmentDB.findAll();
    }

    @PostMapping("/addDepartment")
    public DepartmentModel addDepartment(@RequestBody DepartmentModel departmentModel) {
        log.info("DepartmentController::addDepartment");
        return departmentDB.save(departmentModel);
    }

}
