package com.example.spring.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Permission {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
