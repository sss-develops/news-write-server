package project.goorm.newswriteserver.business.core.bookmark.infrastructure.command;

import org.springframework.data.jpa.repository.JpaRepository;
import project.goorm.newswriteserver.business.core.bookmark.entity.Bookmark;

public interface BookmarkJpaRepository extends JpaRepository<Bookmark, Long> {
}
