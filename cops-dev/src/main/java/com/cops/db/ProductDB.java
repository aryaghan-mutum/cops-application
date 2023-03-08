package com.cops.db;

import com.cops.model.OrderModel;
import com.cops.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductDB extends JpaRepository<ProductModel, Integer> {

}