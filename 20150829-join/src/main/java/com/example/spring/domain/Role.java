package com.example.spring.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany
    private List<Permission> permissions;
}
