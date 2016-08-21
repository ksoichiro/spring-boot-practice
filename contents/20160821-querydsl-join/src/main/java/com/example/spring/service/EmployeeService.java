package com.example.spring.service;

import com.example.spring.domain.Employee;
import com.example.spring.domain.QEmployee;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Path;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.PathBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EntityManager entityManager;

    public Page<Employee> findAll(Pageable pageable) {
        Predicate predicate = QEmployee.employee.name.startsWith("e");

        PathBuilder<Employee> builder = new PathBuilder<>(Employee.class, QEmployee.employee.getMetadata());
        Querydsl querydsl = new Querydsl(entityManager, builder);

        JPQLQuery countQuery = createQuery(predicate);
        JPQLQuery query = querydsl.applyPagination(pageable, createQuery(predicate));

        Path<Employee> path = QEmployee.employee;
        Long total = countQuery.count();

        List<Employee> content = total > pageable.getOffset() ? query.list(path) : Collections.<Employee>emptyList();

        return new PageImpl<>(content, pageable, total);
    }

    private JPAQuery createQuery(Predicate predicate) {
        return new JPAQuery(entityManager)
            .from(QEmployee.employee)
            .leftJoin(QEmployee.employee.department)
            .where(predicate);
    }
}
