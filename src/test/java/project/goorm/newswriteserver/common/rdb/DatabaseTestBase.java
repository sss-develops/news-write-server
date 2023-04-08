package project.goorm.newswriteserver.common.rdb;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = DatabaseTestBase.DataSourceInitializer.class)
public abstract class DatabaseTestBase {

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Container
        private static final MySQLContainer database = new MySQLContainer("mysql:latest");

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            database.start();
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + database.getJdbcUrl(),
                    "spring.datasource.username=" + database.getUsername(),
                    "spring.datasource.password=" + database.getPassword()
            );
        }
    }

    @Autowired
    private RDBInitialization rdbInitialization;

    @BeforeEach
    void setup() {
        rdbInitialization.truncateAllEntity();
    }

}
