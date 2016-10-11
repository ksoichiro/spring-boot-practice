package com.example.spring.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class C {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private B b;
}
