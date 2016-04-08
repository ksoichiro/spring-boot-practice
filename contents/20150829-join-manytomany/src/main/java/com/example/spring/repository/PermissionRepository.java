package com.example.spring.repository;

import com.example.spring.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    @Query("select p from #{#entityName} p inner join p.roles r where r.id = ?1")
    List<Permission> findAllByRoleId(Integer id);
}
