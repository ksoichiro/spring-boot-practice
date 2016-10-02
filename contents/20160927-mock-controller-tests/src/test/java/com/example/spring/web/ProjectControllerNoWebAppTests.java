package com.example.spring.web;

import com.example.spring.domain.Project;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
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
public class ProjectControllerNoWebAppTests extends AbstractControllerTests {
    @StandaloneControllerConfiguration
    static class Config extends DefaultStandaloneControllerConfig {
        // Test target Controller
        @Bean
        ProjectController projectController() {
            return new ProjectController();
        }
    }

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
