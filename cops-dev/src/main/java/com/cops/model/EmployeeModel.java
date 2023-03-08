package com.cops.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "employee")
public class EmployeeModel {
    @Id
    @Column(name="employee_id")
    private String employeeId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private int age;

    @Column(name="email")
    private String email;

    @Column(name="job_name")
    private String jobName;

    @Column(name="contact_number")
    private String contactNumber;

    @Column(name="dob")
    private String dob;

    @Column(name="salary")
    private String salary;

    @Column(name="dep_id")
    private int depId;
}
