package com.tie.component;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
@TestInstance(PER_CLASS)
@Slf4j
public abstract class BaseComponentTest {


    private static final MySQLContainer<?> mySqlContainer = createAndStart();

    @Autowired
    protected MockMvc mockMvc;

    private static MySQLContainer<?> createAndStart() {
        var container = new MySQLContainer<>("mysql:8.0.20")
                .withDatabaseName("tie_db")
                .withUsername("springuser")
                .withPassword("ThePassword")
                .waitingFor(
                        Wait.forLogMessage(".*MySQL init process done.*\\n", 1));
        container.start();
        return container;
    }

    @DynamicPropertySource
    static void mySqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySqlContainer::getUsername);
        registry.add("spring.datasource.password", mySqlContainer::getPassword);
    }


}
