package com.example.spring.repository;

import com.example.spring.App;
import com.example.spring.domain.Permission;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@Slf4j
public class PermissionRepositoryTests {
    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void findAll() {
        List<Permission> permissions = permissionRepository.findAllByRoleId(1);
        assertNotNull(permissions);
        assertEquals(8, permissions.size());

        permissions = permissionRepository.findAllByRoleId(2);
        assertNotNull(permissions);
        assertEquals(5, permissions.size());
    }
}
