package com.cops.controller;

import com.cops.db.SupplierDB;
import com.cops.model.SupplierModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierDB salaryDB;

    @GetMapping("/getSuppliersList")
    public List<SupplierModel> getSuppliersList() {
        log.info("SupplierController::getSuppliersList");
        return salaryDB.findAll();
    }

    @PostMapping("/addSupplier")
    public SupplierModel addSupplier(@RequestBody SupplierModel supplierModel) {
        log.info("SupplierController::addSupplier");
        return salaryDB.save(supplierModel);
    }

    @DeleteMapping("/deleteSupplier")
    public void deleteSupplier(@RequestBody SupplierModel supplierModel) {
        log.info("ProductController::deleteSupplier");
        salaryDB.delete(supplierModel);
    }

    @PutMapping("/updateSupplier")
    public void updateProduct(@RequestBody SupplierModel supplierModel) {
        log.info("ProductController::updateProduct");
        salaryDB.save(supplierModel);
    }
}
