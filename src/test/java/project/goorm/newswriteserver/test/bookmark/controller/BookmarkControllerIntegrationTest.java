package project.goorm.newswriteserver.test.bookmark.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.goorm.newswriteserver.business.core.news.entity.News;
import project.goorm.newswriteserver.business.web.application.bookmark.BookmarkCommand;
import project.goorm.newswriteserver.business.web.presentation.BookmarkCommandAPI;
import project.goorm.newswriteserver.common.config.TestConfig;
import project.goorm.newswriteserver.common.rdb.AbstractContainerTestBase;
import project.goorm.newswriteserver.test.helper.fixture.NewsFixture;
import project.goorm.newswriteserver.test.helper.persistence.PersistenceHelper;

@ActiveProfiles("test")
@Import({TestConfig.class})
@DisplayName("북마크 컨트롤러 통합 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookmarkControllerIntegrationTest extends AbstractContainerTestBase {

    private MockMvc mockMvc;

    @Autowired
    private BookmarkCommand bookmarkCommand;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new BookmarkCommandAPI(bookmarkCommand))
                .build();
    }

    @Autowired
    private PersistenceHelper persistenceHelper;

    @Test
    @DisplayName("북마크를 저장하면 Status코드가 201이다.")
    public void given_news_id_when_save_then_status_created() throws Exception {
        // given - precondition or setup
        News news = persistenceHelper.persist(NewsFixture.createNews());
        Long newsId = news.getNewsId();

        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/bookmark/save/1/"+newsId)
                .contentType(MediaType.APPLICATION_JSON)
        );
        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }
}
