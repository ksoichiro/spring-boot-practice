package com.example.spring.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class B {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private A a;

    @OneToMany(mappedBy = "b")
    private List<C> cList;
}
