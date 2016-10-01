package com.example.spring.web;

import com.example.spring.annotation.ElasticsearchDependentService;
import com.example.spring.domain.Project;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This test maps only one controller (by using standalongSetup() instead of webAppContextSetup()),
 * Elasticsearch node is disabled (by excluding some auto configurations),
 * and default data-h2.sql is disabled.
 */
// To disable Elasticsearch auto configuration, add a special annotation.
// In addition, add custom static Configuration class to control component scan.
@SpringApplicationConfiguration(classes = {NoElasticsearchAutoConfigurationConfiguration.class, ProjectControllerNoWebAppTests.Config.class})
// To insert test fixture data:
@Sql("/data-project.sql")
// To disable inserting default data-{platform}.sql:
@TestPropertySource(properties = "spring.datasource.data: /data-empty.sql")
// With this, all controllers will be mapped, which makes tests slower.
// But in some cases, WebApplicationContext is required.
//@WebAppConfiguration
public class ProjectControllerNoWebAppTests {
    // With @ContextConfiguration, the internal static @Configuration classes will be automatically registered.
    @Configuration
    @ComponentScan(value = "com.example.spring", excludeFilters = {
        // To avoid registration of Application class in src/main
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = SpringBootApplication.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ElasticsearchDependentService.class),
        // With this, you can avoid many Bean registrations, but it's often difficult
        // @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
    })
    @EnableJpaRepositories("com.example.spring.repository")
    @EntityScan("com.example.spring.domain")
    static class Config {
        // Without @WebAppConfiguration and @SpringBootApplication, this definition is required
        @Bean
        ErrorAttributes errorAttributes() {
            return new DefaultErrorAttributes();
        }

        // Test target Controller
        @Bean
        ProjectController projectController() {
            return new ProjectController();
        }
    }

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    private MockMvc mockMvc;

    @Autowired
    private ProjectController projectController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(projectController)
            // To instantiate Pageable
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void list() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/projects"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("list"))
            .andExpect(view().name("projects/list"))
            .andReturn();
        ModelMap modelMap = mvcResult.getModelAndView().getModelMap();
        Object listObj = modelMap.get("list");
        assertThat(listObj, is(notNullValue()));
        assertThat(listObj, is(instanceOf(Page.class)));
        Page<Project> page = (Page<Project>) listObj;
        assertThat(page.getContent().size(), is(3));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void list2() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/projects"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("list"))
            .andExpect(view().name("projects/list"))
            .andReturn();
        ModelMap modelMap = mvcResult.getModelAndView().getModelMap();
        Object listObj = modelMap.get("list");
        assertThat(listObj, is(notNullValue()));
        assertThat(listObj, is(instanceOf(Page.class)));
        Page<Project> page = (Page<Project>) listObj;
        assertThat(page.getContent().size(), is(3));
    }
}
