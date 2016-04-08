package com.example.spring.service;

import com.example.spring.domain.Permission;
import com.example.spring.domain.QPermission;
import com.example.spring.domain.QRole;
import com.example.spring.domain.QRolePermission;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Path;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.PathBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
public class PermissionService {
    @PersistenceContext
    private EntityManager em;

    public Page<Permission> findAllByRoleId(Integer id, Pageable pageable) {
        QRole role = QRole.role;
        Predicate predicate = role.id.eq(id);

        PathBuilder<Permission> builder = new PathBuilder<Permission>(Permission.class, QPermission.permission.getMetadata());
        Querydsl querydsl = new Querydsl(em, builder);

        JPQLQuery countQuery = createQuery(predicate);
        JPQLQuery query = querydsl.applyPagination(pageable, createQuery(predicate));

        Path<Permission> path = QPermission.permission;
        Long total = countQuery.count();
        List<Permission> content = total > pageable.getOffset() ? query.list(path) : Collections.<Permission>emptyList();

        return new PageImpl<Permission>(content, pageable, total);
    }

    public List<Permission> findAllByRoleId(Integer id) {
        QRole role = QRole.role;
        return createQuery(role.id.eq(id)).list(QPermission.permission);
    }

    public JPAQuery createQuery(Predicate predicate) {
        QPermission permission = QPermission.permission;
        QRolePermission rolePermission = QRolePermission.rolePermission;
        QRole role = QRole.role;
        return new JPAQuery(em)
            .from(permission)
            .leftJoin(permission.rolePermission, rolePermission)
            .leftJoin(rolePermission.role, role)
            .where(predicate);
    }
}
