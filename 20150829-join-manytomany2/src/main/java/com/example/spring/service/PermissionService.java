package com.example.spring.service;

import com.example.spring.domain.Permission;
import com.example.spring.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> findAllByRoleId(Integer id) {
        return permissionRepository.findAllByRoleId(id);
    }
}
