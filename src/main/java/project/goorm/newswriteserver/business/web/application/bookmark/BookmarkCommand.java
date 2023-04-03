package project.goorm.newswriteserver.business.web.application.bookmark;

import project.goorm.newswriteserver.business.core.bookmark.entity.Bookmark;

public interface BookmarkCommand {
    Bookmark saveBookmark(Long newsId, Long memberId);
}
