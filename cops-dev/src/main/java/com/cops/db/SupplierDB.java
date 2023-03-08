package com.cops.db;

import com.cops.model.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SupplierDB extends JpaRepository<SupplierModel, Integer> {
}