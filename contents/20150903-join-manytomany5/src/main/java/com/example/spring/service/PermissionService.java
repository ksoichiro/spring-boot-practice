package com.example.spring.service;

import com.example.spring.domain.Permission;
import com.example.spring.domain.QPermission;
import com.example.spring.domain.QRole;
import com.example.spring.domain.QRolePermission;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PermissionService {
    @PersistenceContext
    private EntityManager em;

    public List<Permission> findAllByRoleId(Integer id) {
        QPermission permission = QPermission.permission;
        QRolePermission rolePermission = QRolePermission.rolePermission;
        QRole role = QRole.role;
        return new JPAQueryFactory(em)
            .selectFrom(permission)
            .leftJoin(permission.rolePermission, rolePermission)
            .leftJoin(rolePermission.role, role)
            .where(role.id.eq(id))
            .fetch();
    }
}
