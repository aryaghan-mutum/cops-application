package com.cops.db;

import com.cops.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerDB extends JpaRepository<CustomerModel, Integer> {

}