package com.example.spring.repository;

import com.example.spring.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    @Query(value = "select * from permission p "
        + " inner join role_permission rp on rp.permission_id = p.id "
        + " inner join role r on r.id = rp.role_id "
        + " where r.id = ?1",
        nativeQuery = true)
    List<Permission> findAllByRoleId(Integer id);
}
