package project.goorm.newswriteserver.business.core.news.infrastructure.command;

import org.springframework.data.jpa.repository.JpaRepository;
import project.goorm.newswriteserver.business.core.news.entity.News;

public interface NewsJpaRepository extends JpaRepository<News, Long> {
}
