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

    @OneToMany
    @JoinTable(name = "RolePermission",
        joinColumns = @JoinColumn(name = "permission_id"),
        inverseJoinColumns = @JoinColumn(name = "id"))
    private List<RolePermission> rolePermission;
}
