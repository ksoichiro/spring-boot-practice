package com.example.spring.javassist;

import com.example.spring.annotation.PersistentConditionallyOnProperty;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.security.ProtectionDomain;

@Slf4j
public class ClassDefinitionModifier implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationPreparedEvent) {
            Environment environment = ((ApplicationPreparedEvent) event).getApplicationContext().getEnvironment();
            try {
                modifyClasses(environment);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void modifyClasses(Environment environment) throws Exception {
        // TODO This class list should be made on build time.
        // Listing classes in a specific package before loading them is difficult
        // for Spring Boot apps, because the Executable Jar includes nested Jars and
        // ClassLoader is made for that.
        String[] classNames = new String[]{"com.example.spring.domain.Os", "com.example.spring.domain.Foo"};

        ClassPool pool = ClassPool.getDefault();
        for (String className : classNames) {
            CtClass cc = pool.get(className);
            ClassFile ccFile = cc.getClassFile();
            ConstPool constPool = ccFile.getConstPool();

            for (CtField cf : cc.getDeclaredFields()) {
                AnnotationsAttribute attr = (AnnotationsAttribute) cf.getFieldInfo().getAttribute(AnnotationsAttribute.visibleTag);
                if (attr == null) {
                    // No annotation is defined for this field
                    continue;
                }
                for (Annotation a : attr.getAnnotations()) {
                    if (a.getTypeName().equals(PersistentConditionallyOnProperty.class.getCanonicalName())) {
                        String value = ((StringMemberValue) a.getMemberValue("value")).getValue();
                        String prop = environment.getProperty(value);
                        Annotation[] annotations;
                        if ("true".equals(prop)) {
                            // Must be persistent
                            annotations = new Annotation[attr.getAnnotations().length - 1];

                            for (int i = 0, j = 0; i < attr.getAnnotations().length; i++) {
                                Annotation aa = attr.getAnnotations()[i];
                                if (!aa.getTypeName().equals(PersistentConditionallyOnProperty.class.getCanonicalName())) {
                                    annotations[j] = attr.getAnnotations()[i];
                                    j++;
                                }
                            }
                        } else {
                            // Must be transient
                            annotations = new Annotation[attr.getAnnotations().length];

                            Annotation alternative = new Annotation("javax.persistence.Transient", constPool);
                            for (int i = 0; i < attr.getAnnotations().length; i++) {
                                Annotation aa = attr.getAnnotations()[i];
                                if (aa.getTypeName().equals(PersistentConditionallyOnProperty.class.getCanonicalName())) {
                                    annotations[i] = alternative;
                                    log.info("Added @Transient to {}.{}", className, cf.getName());
                                } else {
                                    annotations[i] = attr.getAnnotations()[i];
                                }
                            }
                        }
                        attr.setAnnotations(annotations);
                        Class thisClass = this.getClass();
                        ClassLoader loader = thisClass.getClassLoader();
                        ProtectionDomain domain = thisClass.getProtectionDomain();
                        cc.toClass(loader, domain);
                        break;
                    }
                }
            }
        }

        // If the class names list are broken, it will be a critical error on runtime.
        // To avoid it, read all the Entity classes here to check if
        // all PersistentConditionallyOnProperty are processed.
        // If some of them are not processed, the app should be aborted.
        ClassPathScanningCandidateComponentProvider provider
            = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        for (BeanDefinition bd : provider.findCandidateComponents("com.example.spring")) {
            Class c = Class.forName(bd.getBeanClassName());
            for (Field f : c.getDeclaredFields()) {
                java.lang.annotation.Annotation a = f.getAnnotation(PersistentConditionallyOnProperty.class);
                if (a != null) {
                    throw new AssertionError(
                        "There are unhandled PersistentConditionallyOnProperty. "
                            + "Please check if PersistentConditionallyOnProperty-annotated classes are all listed in "
                            + this.getClass().getCanonicalName() + ". "
                            + "This might cause critical errors on runtime, so don't try to catch this to proceed.");
                }
            }
        }
    }
}
