package project.goorm.newswriteserver.test.bookmark.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import project.goorm.newswriteserver.business.core.bookmark.entity.Bookmark;
import project.goorm.newswriteserver.business.core.news.entity.News;
import project.goorm.newswriteserver.business.web.application.bookmark.service.BookmarkCommandService;
import project.goorm.newswriteserver.common.rdb.AbstractContainerTestBase;
import project.goorm.newswriteserver.test.helper.fixture.NewsFixture;
import project.goorm.newswriteserver.test.helper.persistence.PersistenceHelper;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.*;

@ActiveProfiles({"test"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("북마크 서비스레이어 통합 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BookmarkServiceMockTest extends AbstractContainerTestBase {

    @Autowired
    private BookmarkCommandService bookmarkCommandService;

    static final int PORT = 8080;
    public static WireMockServer wireMockServer = new WireMockServer(options().port(PORT));

    @Autowired
    private PersistenceHelper persistenceHelper;

    @DynamicPropertySource
    public static void addUrlProperties(DynamicPropertyRegistry registry) {
        registry.add("api.authentication-server.url", () -> "localhost:" + PORT);
    }

    @BeforeAll
    public static void beforeAll() {
        wireMockServer.start();
        WireMock.configureFor("localhost", PORT);
    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    }

    @AfterEach
    public void afterEach() {
        wireMockServer.resetAll();
    }

    @Test
    @DisplayName("북마크 저장을 하면 북마크가 생긴다.")
    public void given_memberid_newsid_when_save_bookmark_then_is_not_null() {
        Long memberId = 1L;
        wireMockServer.stubFor(get(urlMatching("/api/member/exist/"+memberId))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("true"))
        );

        News persist = persistenceHelper.persist(NewsFixture.createNews());
        Bookmark bookmark = bookmarkCommandService.saveBookmark(1L, memberId);

        assertThat(bookmark).isNotNull();
    }

    @Test
    @DisplayName("Auth서버랑 연결되지 않으면 에러를 반환한다.")
    public void when_bookmark_save_then_throw_error() {
        assertThatThrownBy(() -> bookmarkCommandService.saveBookmark(1L, 1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("페인 클라이언트 에러!");
    }
}
