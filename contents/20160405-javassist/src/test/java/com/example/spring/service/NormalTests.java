package com.example.spring.service;

import com.example.spring.domain.Os;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/*
 * Os class is modified by ClassDefinitionModifier when Spring Context is prepared,
 * so any test case classes using modified classes should launch after that.
 * When this class is run as a pure JUnit test case, the tests will fail due to javassist.CannotCompileException.
 * To avoid this issue, this class extends a custom base test class EmptySpringApplicationTests
 * which is annotated with SpringApplicationConfiguration and has no auto-configuration to be launched quickly.
 */
@Slf4j
public class NormalTests extends EmptySpringApplicationTests {
    @Test
    public void test() throws Exception {
        Os os = new Os();

        Field field = Os.class.getDeclaredField("foo");
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            log.info("foo: annotated with: {}", annotation.annotationType().getCanonicalName());
        }
    }
}
