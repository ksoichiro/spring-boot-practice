package com.example.spring.domain;

import com.example.spring.annotation.PersistentConditionallyOnProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Foo {
    @Id
    private Integer id;

    @PersistentConditionallyOnProperty("application.persistent-conditionally.foo-bar")
    private String bar;
}
