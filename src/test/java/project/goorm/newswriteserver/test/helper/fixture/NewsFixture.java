package project.goorm.newswriteserver.test.helper.fixture;

import project.goorm.newswriteserver.business.core.news.entity.News;

import java.time.Instant;

public class NewsFixture {

    public static News createNews() {
        return new News(
                "title",
                "des",
                1L,
                "www.ttt.com",
                "www.ttt.com",
                Instant.now(),
                Instant.now()
        );
    }
}
