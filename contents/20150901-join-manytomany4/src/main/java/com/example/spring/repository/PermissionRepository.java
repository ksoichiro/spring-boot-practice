package com.example.spring.repository;

import com.example.spring.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Join;
import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {
    default List<Permission> findAllByRoleId(Integer id) {
        return findAll((root, query, cb) -> {
            // In this pattern, you can set restriction conditions to association table rolePermission.
            Join rp = root.join("rolePermission");
            Join r = rp.join("role");
            return cb.equal(r.get("id"), id);
        });
    }
}
