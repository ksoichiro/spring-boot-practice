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

    @ManyToMany
    @JoinTable(name = "RolePermission",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
