package project.goorm.newswriteserver.test.helper.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.goorm.newswriteserver.business.core.news.entity.News;
import project.goorm.newswriteserver.business.core.news.infrastructure.command.NewsJpaRepository;

@Component
public class PersistenceHelper {

    @Autowired
    private NewsJpaRepository newsJpaRepository;

    public News persist(News news) {
        return newsJpaRepository.save(news);
    }
}
