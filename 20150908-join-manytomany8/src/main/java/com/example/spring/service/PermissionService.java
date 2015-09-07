package com.example.spring.service;

import com.example.spring.domain.Permission;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class PermissionService {
    @PersistenceContext
    private EntityManager em;

    public List<Permission> findAllByRoleId(Integer id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Permission> query = cb.createQuery(Permission.class);
        Root<Permission> root = query.from(Permission.class);
        Join rolePermission = root.join("rolePermission", javax.persistence.criteria.JoinType.LEFT);
        Join role = rolePermission.join("role", javax.persistence.criteria.JoinType.LEFT);
        return em.createQuery(
            query.<Permission>where(
                cb.and(
                    cb.equal(role.get("id"), id),
                    cb.isNull(root.get("deletedAt")),
                    cb.isNull(rolePermission.get("deletedAt")),
                    cb.isNull(role.get("deletedAt"))
                )))
            .getResultList();
    }
}
