package project.goorm.newswriteserver.business.web.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.goorm.newswriteserver.business.core.bookmark.entity.Bookmark;
import project.goorm.newswriteserver.business.web.application.bookmark.BookmarkCommand;

@RestController
@RequestMapping("/api/bookmark")
public class BookmarkCommandAPI {

    private final BookmarkCommand bookmarkCommandService;

    public BookmarkCommandAPI(BookmarkCommand bookmarkCommandService) {
        this.bookmarkCommandService = bookmarkCommandService;
    }

    @PostMapping("/save/{memberId}/{newsId}")
    public ResponseEntity<Void> saveBookmark(
            @PathVariable("memberId") Long memberId,
            @PathVariable("newsId") Long newsId
    ) {
        Bookmark bookmark = bookmarkCommandService.saveBookmark(
                newsId,
                memberId
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
