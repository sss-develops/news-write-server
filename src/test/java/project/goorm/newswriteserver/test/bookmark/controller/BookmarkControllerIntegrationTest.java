package project.goorm.newswriteserver.test.bookmark.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import project.goorm.newswriteserver.business.core.news.entity.News;
import project.goorm.newswriteserver.common.rdb.DatabaseTestBase;
import project.goorm.newswriteserver.test.helper.fixture.NewsFixture;
import project.goorm.newswriteserver.test.helper.persistence.PersistenceHelper;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@ActiveProfiles({"test"})
@DisplayName("북마크 API 통합 테스트")
@ExtendWith(RestDocumentationExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookmarkControllerIntegrationTest extends DatabaseTestBase {

    private MockMvc mockMvc;

    static final int PORT = 8091;
    public static WireMockServer wireMockServer = new WireMockServer(options().port(PORT));

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

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider documentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(documentation))
                .build();
    }

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Test
    @DisplayName("북마크를 저장하면 Status코드가 201이다.")
    public void given_news_id_when_save_then_status_created() throws Exception {
        wireMockServer.stubFor(get(urlMatching("/api/member/exist/1"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("true"))
        );

        News news = persistenceHelper.persist(NewsFixture.createNews());
        Long newsId = news.getNewsId();

        ResultActions response = mockMvc.perform(RestDocumentationRequestBuilders.post("/api/bookmark/save/{memberId}/{newsId}", 1L, newsId)
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(document("post-create",
                        pathParameters(
                                parameterWithName("memberId").description("member id"),
                                parameterWithName("newsId").description("news id")
                        )
                ));
    }
}
