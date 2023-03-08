package com.cops.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "products")
public class ProductModel {
    @Id
    @Column(name="product_id")
    private int productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="supplier_id")
    private int supplierId;

    @Column(name="category_id")
    private int categoryId;

    @Column(name="unit")
    private String unit;

    @Column(name="price")
    private double price;
}
