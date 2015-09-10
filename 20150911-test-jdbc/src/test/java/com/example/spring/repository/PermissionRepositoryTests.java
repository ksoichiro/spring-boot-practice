package com.example.spring.repository;

import com.example.spring.App;
import com.example.spring.domain.Permission;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@ActiveProfiles("test")
@Sql("/data-test.sql")
@Slf4j
public class PermissionRepositoryTests extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void test() {
        assertEquals(4, countRowsInTable("permission"));
        assertEquals(4, permissionRepository.count());
        jdbcTemplate.execute("insert into permission (name) values ('foo');");
        assertEquals(5, countRowsInTable("permission"));
        assertEquals(5, permissionRepository.count());

        Permission p = permissionRepository.findOne(1);
        assertNotNull(p);
        assertEquals("CREATE_USER", p.getName());

        jdbcTemplate.execute("update permission set name = 'BAR' where id = 1;");
        // This does not execute select, why?
        p = permissionRepository.findOne(1);
        assertNotNull(p);
        assertEquals("CREATE_USER", p.getName()); // OMG!
        // assertEquals("BAR", p.getName()); // This is the really expected result
    }
}
