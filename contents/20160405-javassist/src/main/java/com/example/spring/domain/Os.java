package com.example.spring.domain;

import com.example.spring.annotation.PersistentConditionallyOnProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Os {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @PersistentConditionallyOnProperty("application.persistent-conditionally.os-foo")
    private String foo;
}
