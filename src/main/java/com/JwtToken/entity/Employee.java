package com.JwtToken.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="city", nullable=false)
    private String city;

    @Column(name="first_dept", nullable=false)
    private String dept;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)          //Foregin key in child table
    private Department department;

}
