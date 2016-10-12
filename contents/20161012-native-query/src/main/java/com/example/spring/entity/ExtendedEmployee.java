package com.example.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This is not a managed entity class.
 * To instantiate with returning {@code Object[]} from repository,
 * this class defines a constructor that takes {@code Object[]}.
 */
@Data
@AllArgsConstructor
public class ExtendedEmployee {
    private Integer id;
    private String name;
    private String departmentName;

    public ExtendedEmployee(Object[] objects) {
        this((Integer) objects[0], (String) objects[1], (String) objects[2]);
    }
}
