package com.cops.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "suppliers")
public class SupplierModel {
    @Id
    @Column(name="supplier_id")
    private Integer supplierId;

    @Column(name="supplier_name")
    private String supplierName;

    @Column(name="contact_name")
    private String contactName;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="country")
    private String country;
}
