package com.example.spring.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private boolean manager;

    @ManyToOne
    private Department department;

    private Integer definedColumn;
}
