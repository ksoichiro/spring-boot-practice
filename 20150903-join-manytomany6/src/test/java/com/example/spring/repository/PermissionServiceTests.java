package com.example.spring.repository;

import com.example.spring.App;
import com.example.spring.domain.Permission;
import com.example.spring.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@Slf4j
public class PermissionServiceTests {
    @Autowired
    private PermissionService permissionService;

    @Test
    public void findAll() {
        List<Permission> permissions = permissionService.findAllByRoleId(1);
        assertNotNull(permissions);
        assertEquals(8, permissions.size());

        permissions = permissionService.findAllByRoleId(2);
        assertNotNull(permissions);
        assertEquals(5, permissions.size());
    }

    @Test
    public void findAllWithPageable() {
        Pageable pageable = new PageRequest(0, 3);
        Page<Permission> page = permissionService.findAllByRoleId(2, pageable);
        assertEquals(2, page.getTotalPages());
        assertEquals(0, page.getNumber());
        assertEquals(3, page.getNumberOfElements());
    }
}
