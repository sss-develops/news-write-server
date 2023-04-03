package project.goorm.newswriteserver.business.web.application.bookmark.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.goorm.newswriteserver.business.core.bookmark.entity.Bookmark;
import project.goorm.newswriteserver.business.core.bookmark.infrastructure.command.BookmarkJpaRepository;
import project.goorm.newswriteserver.business.core.news.entity.News;
import project.goorm.newswriteserver.business.core.news.infrastructure.command.NewsJpaRepository;
import project.goorm.newswriteserver.business.web.application.bookmark.BookmarkCommand;
import project.goorm.newswriteserver.business.web.application.member.MemberFeignClient;


@Service
@Slf4j
public class BookmarkCommandService implements BookmarkCommand {

    private final NewsJpaRepository newsJpaRepository;
    private final MemberFeignClient memberFeignClient;
    private final BookmarkJpaRepository bookmarkJpaRepository;

    public BookmarkCommandService(
            NewsJpaRepository newsJpaRepository,
            MemberFeignClient memberFeignClient,
            BookmarkJpaRepository bookmarkJpaRepository
    ) {
        this.newsJpaRepository = newsJpaRepository;
        this.memberFeignClient = memberFeignClient;
        this.bookmarkJpaRepository = bookmarkJpaRepository;
    }

    @Override
    public Bookmark saveBookmark(Long newsId, Long memberId) {
        if (!memberFeignClient.memberExistsByMemberId(memberId)) {
            throw new IllegalArgumentException("해당 id의 멤버가 존재하지 않습니다.");
        }
        News news = newsJpaRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 뉴스가 존재하지 않습니다."));
        Bookmark bookmark = new Bookmark(
                news.getNewsId(),
                memberId
        );
        Bookmark savedBookmark = bookmarkJpaRepository.save(bookmark);
        return savedBookmark;
    }
}
