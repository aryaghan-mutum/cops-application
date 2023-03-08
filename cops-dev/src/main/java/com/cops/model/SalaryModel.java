package com.cops.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "salary")
public class SalaryModel {
    @Id
    @Column(name="base_salary")
    private double baseSalary;

    @Column(name="increment_salary")
    private double incrementSalary;
}
