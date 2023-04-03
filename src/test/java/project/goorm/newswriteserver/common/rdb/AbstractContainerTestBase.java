package project.goorm.newswriteserver.common.rdb;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractContainerTestBase {

    static final MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer("mysql:latest")
                .withUsername("username")
                .withPassword("password")
                .withDatabaseName("goormtest");
        MY_SQL_CONTAINER.start(); // MYSQL컨테이너 명시적으로 시작
    }

    @Autowired
    private RDBInitialization rdbInitialization;

    @BeforeEach
    void setUP() {
        rdbInitialization.truncateAllEntity();
    }
}
