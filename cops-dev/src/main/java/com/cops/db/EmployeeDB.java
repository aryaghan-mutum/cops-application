package com.cops.db;

import com.cops.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeDB extends JpaRepository<EmployeeModel, String> {

}