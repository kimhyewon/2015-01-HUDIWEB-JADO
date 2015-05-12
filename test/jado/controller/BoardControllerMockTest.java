package jado.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import jado.model.ArticleComment;
import jado.service.ArticleService;
import jado.service.ShopService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@RunWith(MockitoJUnitRunner.class)
public class BoardControllerMockTest {
	
	private static final Logger log = LoggerFactory.getLogger(BoardControllerMockTest.class);
	
	private static final String URI_BOARD_ANSWER_SAVE = "/board/answer/save";
	private static final String CONTENT = "hello model";
	private static final String SHOP_URI = "www.shopurl.com";
	private static final String STR_USER_ID = "100";
	private static final String STR_ARTICLE_ID = "10";
	private static final String STR_BOARD_ID = "1";

	@InjectMocks
	private BoardController boardController;
	
	@Mock
	private ArticleService articleService;
	
	@Mock
	private MockHttpSession session;
	
	@Mock
	private Model model;
	
	@Spy
	private ShopService shopService;
	
	@Captor
	private ArgumentCaptor<ArticleComment> insertArticleCommentArgs;
	
	@Captor
	private ArgumentCaptor<Integer> articleIdArg;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
	}
	
	@Test
	public void commentPost_S_ModelAttribute로_값이_잘_받아지는지() throws Exception {

		this.mockMvc.perform(post(URI_BOARD_ANSWER_SAVE)
				.param("shopUrl", SHOP_URI)
				.param("boardId", STR_BOARD_ID)
				.param("articleId", STR_ARTICLE_ID)
				.param("userId", STR_USER_ID)
				.param("content", CONTENT))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/board/show/"+SHOP_URI+"/"+STR_BOARD_ID+"/" + STR_ARTICLE_ID));

		verify(articleService).insertArticleCommnet(insertArticleCommentArgs.capture());
		ArticleComment expectedArticleComment = insertArticleCommentArgs.getValue();
		
		assertThat(expectedArticleComment.getArticleId(), equalTo(Integer.parseInt(STR_ARTICLE_ID)));
		assertThat(expectedArticleComment.getUserId(), equalTo(STR_USER_ID));
		assertThat(expectedArticleComment.getContent(), equalTo(CONTENT));

	}
}
