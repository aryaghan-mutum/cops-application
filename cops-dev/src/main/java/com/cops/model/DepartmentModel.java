package com.cops.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "department")
public class DepartmentModel {
    @Id
    @Column(name="dep_id")
    private int depId;

    @Column(name="dep_name")
    private String depName;

    @Column(name="dep_location")
    private String depLocation;
}
