package com.example.spring.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class RolePermission {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Permission permission;
}
