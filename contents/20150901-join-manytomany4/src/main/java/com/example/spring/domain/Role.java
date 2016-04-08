package com.example.spring.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany
    private List<RolePermission> rolePermission;
}
