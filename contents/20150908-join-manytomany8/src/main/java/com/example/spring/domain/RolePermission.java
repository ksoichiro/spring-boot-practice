package com.example.spring.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class RolePermission {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    private Date deletedAt;
}
