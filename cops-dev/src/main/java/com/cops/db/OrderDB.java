package com.cops.db;

import com.cops.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderDB extends JpaRepository<OrderModel, Integer> {
}