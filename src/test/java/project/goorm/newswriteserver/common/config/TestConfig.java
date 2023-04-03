package project.goorm.newswriteserver.common.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import project.goorm.newswriteserver.business.core.bookmark.infrastructure.command.BookmarkJpaRepository;
import project.goorm.newswriteserver.business.core.news.infrastructure.command.NewsJpaRepository;
import project.goorm.newswriteserver.business.web.application.bookmark.BookmarkCommand;
import project.goorm.newswriteserver.business.web.application.bookmark.service.BookmarkCommandService;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public BookmarkCommand bookmarkCommand(NewsJpaRepository newsJpaRepository, BookmarkJpaRepository bookmarkJpaRepository) {
        MemberFeignClientImpl memberFeignClient = new MemberFeignClientImpl();
        return new BookmarkCommandService(
                newsJpaRepository,
                memberFeignClient,
                bookmarkJpaRepository
        );
    }
}
