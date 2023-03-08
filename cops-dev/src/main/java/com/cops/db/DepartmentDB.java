package com.cops.db;

import com.cops.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DepartmentDB extends JpaRepository<DepartmentModel, Integer> {

}