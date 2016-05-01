package com.example.spring.service;

import com.example.spring.App;
import com.example.spring.domain.Os;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@Sql("/fixture.sql")
@Slf4j
public class OsServiceTests {
    @Autowired
    private OsService osService;

    @Test
    public void findAll() throws Exception {
        List<Os> result = osService.findAll();
        assertNotNull(result);
        assertEquals(5, result.size());

        Field field = Os.class.getDeclaredField("foo");
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            log.info("foo: annotated with: {}", annotation.annotationType().getCanonicalName());
        }
    }
}
