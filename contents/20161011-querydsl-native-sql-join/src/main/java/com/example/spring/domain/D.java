package com.example.spring.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class D {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
