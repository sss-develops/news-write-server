package project.goorm.newswriteserver.test.bookmark.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import project.goorm.newswriteserver.business.core.bookmark.entity.Bookmark;
import project.goorm.newswriteserver.business.core.news.entity.News;
import project.goorm.newswriteserver.business.core.news.infrastructure.command.NewsJpaRepository;
import project.goorm.newswriteserver.business.web.application.bookmark.BookmarkCommand;
import project.goorm.newswriteserver.common.config.TestConfig;
import project.goorm.newswriteserver.common.rdb.AbstractContainerTestBase;

import java.time.Instant;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Import({TestConfig.class})
@ActiveProfiles("eureka-test")
public class BookmarkServiceIntegrationTest extends AbstractContainerTestBase {


    @Autowired
    private BookmarkCommand bookmarkCommand;

    @Autowired
    private NewsJpaRepository newsJpaRepository;


    @Test
    @DisplayName("북마크를 저장하면 Bookmark객체의 id값이 생긴다.")
    public void given_news_id_and_member_id_when_then() {
        // when - action or the behaviour that we are going test
        News save = newsJpaRepository.save(
                new News(
                        "title",
                        "des",
                        1L,
                        "com",
                        "com",
                        Instant.now(),
                        Instant.now()
                )
        );
        Bookmark bookmark = bookmarkCommand.saveBookmark(1L, save.getNewsId());

        // then - verify the output
        assertThat(bookmark.getBookmarkId()).isGreaterThan(0L);
    }

    @Test
    @DisplayName("북마크를 저장할 떄 news의 id값이 존재하지 않는 값이면 예외를 던진다.")
    public void when_news_not_exist_then_throw_exception() {
        assertThatThrownBy(() -> bookmarkCommand.saveBookmark(1L, 2L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 id의 뉴스가 존재하지 않습니다.");
    }
}
