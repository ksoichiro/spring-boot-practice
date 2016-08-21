package com.example.spring.service;

import com.example.spring.domain.Department;
import com.example.spring.domain.sql.SDepartment;
import com.example.spring.domain.sql.SEmployee;
import com.example.spring.service.sql.QuerydslSQL;
import com.example.spring.service.sql.SQLTemplatesService;
import com.mysema.query.jpa.sql.JPASQLQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.Projections;
import com.mysema.query.types.path.PathBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SQLTemplatesService sqlTemplatesService;

    public Page<Department> findAll(Pageable pageable) {
        Predicate predicate = null; // TODO

        SDepartment department = SDepartment.department;
        SEmployee employee = SEmployee.employee;

        PathBuilder<Department> builder = new PathBuilder<>(Department.class, department.getMetadata());
        QuerydslSQL querydsl = new QuerydslSQL(builder);

        JPASQLQuery query = querydsl.applyPagination(pageable, createQuery(predicate));
        Long total = createBaseQuery(predicate).count();

        List<Department> content =
            query.orderBy(department.id.asc()).list(
                Projections.constructor(
                    Department.class,
                    department.id,
                    department.name,
                    // SQLExpressions.groupConcat() is not available in Querydsl 3.6.7
                    sqlTemplatesService.groupConcat(employee.name, employee.id.asc())
                ));

        return new PageImpl<>(content, pageable, total);
    }

    private JPASQLQuery createBaseQuery(Predicate predicate) {
        SDepartment department = SDepartment.department;
        JPASQLQuery query = new JPASQLQuery(entityManager, sqlTemplatesService.getTemplates()).from(department);
        if (predicate != null) {
            query.where(predicate);
        }
        return query;
    }

    private JPASQLQuery createQuery(Predicate predicate) {
        SDepartment department = SDepartment.department;
        SEmployee employee = SEmployee.employee;
        JPASQLQuery query = createBaseQuery(predicate)
            .leftJoin(employee).on(employee.departmentId.eq(department.id))
            .groupBy(department.id);
        if (predicate != null) {
            query.where(predicate);
        }
        return query;
    }
}
