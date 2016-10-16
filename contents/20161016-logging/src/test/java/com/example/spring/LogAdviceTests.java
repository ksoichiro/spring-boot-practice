package com.example.spring;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebAppConfiguration
public class LogAdviceTests {
    @Rule
    public OutputCapture capture = new OutputCapture();

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
            .build();
    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/projects"))
            .andExpect(status().isOk());
        assertThat(capture.toString(), containsString("ProjectController#list"));
    }

    @Test
    public void listWithException() throws Exception {
        try {
            mockMvc.perform(get("/projects/ex"))
                .andExpect(status().is5xxServerError());
            fail();
        } catch (Exception ignore) {
            assertThat(capture.toString(), containsString("ProjectController#listWithException This is a test exception"));
            assertThat(capture.toString(), containsString("java.lang.RuntimeException: This is a test exception"));
        }
    }

    @Test
    public void createWithValidationError() throws Exception {
        mockMvc.perform(get("/projects/create"))
            .andExpect(status().isOk());
        assertThat(capture.toString(), containsString("ProjectController#create [name は入力必須です, パラメータfooを持つメッセージです]"));
    }

    @Test
    public void createWithValidationErrorAndException() throws Exception {
        try {
            mockMvc.perform(get("/projects/create/ex"))
                .andExpect(status().is5xxServerError());
            fail();
        } catch (Exception ignore) {
            assertThat(capture.toString(), containsString("ProjectController#createWithException [name は入力必須です, パラメータfooを持つメッセージです] This is a test exception"));
            assertThat(capture.toString(), containsString("java.lang.RuntimeException: This is a test exception"));
        }
    }

    @Test
    public void createWithoutValidationError() throws Exception {
        mockMvc.perform(get("/projects/create").param("name", "foo"))
            .andExpect(status().isOk());
        assertThat(capture.toString(), containsString("ProjectController#create"));
    }

    @Test
    public void createWithoutValidationErrorWithException() throws Exception {
        try {
            mockMvc.perform(get("/projects/create/ex").param("name", "foo"))
                .andExpect(status().is5xxServerError());
            fail();
        } catch (Exception ignore) {
            assertThat(capture.toString(), containsString("ProjectController#createWithException - This is a test exception"));
            assertThat(capture.toString(), containsString("java.lang.RuntimeException: This is a test exception"));
        }
    }

    @Test
    public void createWithoutValidationErrorWithServiceException() throws Exception {
        mockMvc.perform(get("/projects/create").param("name", "bar"))
            .andExpect(status().isOk());
        assertThat(capture.toString(), containsString("ProjectController#create [エラーがあります] Service throws exception"));
        assertThat(capture.toString(), containsString("java.lang.IllegalStateException: Service throws exception"));
    }
}
