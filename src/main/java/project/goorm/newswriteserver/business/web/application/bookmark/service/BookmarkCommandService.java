package project.goorm.newswriteserver.business.web.application.bookmark.service;

import org.springframework.stereotype.Service;
import project.goorm.newswriteserver.business.core.bookmark.entity.Bookmark;
import project.goorm.newswriteserver.business.core.bookmark.infrastructure.command.BookmarkJpaRepository;
import project.goorm.newswriteserver.business.core.news.infrastructure.command.NewsJpaRepository;
import project.goorm.newswriteserver.business.web.application.bookmark.BookmarkCommand;
import project.goorm.newswriteserver.business.web.application.member.feign.MemberFeignClientService;


@Service
public class BookmarkCommandService implements BookmarkCommand {

    private final NewsJpaRepository newsJpaRepository;
    private final MemberFeignClientService memberFeignClientService;
    private final BookmarkJpaRepository bookmarkJpaRepository;

    public BookmarkCommandService(
            NewsJpaRepository newsJpaRepository,
            MemberFeignClientService memberFeignClientService,
            BookmarkJpaRepository bookmarkJpaRepository
    ) {
        this.newsJpaRepository = newsJpaRepository;
        this.memberFeignClientService = memberFeignClientService;
        this.bookmarkJpaRepository = bookmarkJpaRepository;
    }

    @Override
    public Bookmark saveBookmark(Long newsId, Long memberId) {
        if (!memberFeignClientService.existByMemberId(memberId)) {
            throw new IllegalArgumentException("해당 id의 멤버가 존재하지 않습니다.");
        }

        if (!newsJpaRepository.existsById(newsId)) {
            throw new IllegalArgumentException("해당 id의 뉴스가 존재하지 않습니다.");
        }

        Bookmark bookmark = new Bookmark(
                newsId,
                memberId
        );
        Bookmark savedBookmark = bookmarkJpaRepository.save(bookmark);
        return savedBookmark;
    }
}
