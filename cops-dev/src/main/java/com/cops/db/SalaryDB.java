package com.cops.db;

import com.cops.model.SalaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SalaryDB extends JpaRepository<SalaryModel, Double> {
}