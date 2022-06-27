package com.example.projecteucyonjavatribesb;

import com.example.projecteucyonjavatribesb.configuration.SecurityConfig;
import com.example.projecteucyonjavatribesb.filter.CustomAuthenticationFilter;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import com.example.projecteucyonjavatribesb.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SecurityConfig.class, PlayerRepository.class, PlayerService.class, ProjectEucyonJavaTribesBApplication.class, CustomAuthenticationFilter.class})
@WebAppConfiguration(value =
        "java/com/example/projecteucyonjavatribesb/ProjectEucyonJavaTribesBApplication.java"
)
public class TribesLoginTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.notNull(servletContext, "ServletContext is null");
        Assert.isTrue(servletContext instanceof MockServletContext, "ServletContext not instance of MockServletContext");
        Assert.notNull(webApplicationContext.getBean("login"), "WebApplicationContext bean null");
    }
}
