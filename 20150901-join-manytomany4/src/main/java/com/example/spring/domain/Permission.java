package com.example.spring.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Permission {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "permission")
    private List<RolePermission> rolePermission;
}
