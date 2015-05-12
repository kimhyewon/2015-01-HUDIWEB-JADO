package jado.controller;

import jado.model.Article;
import jado.model.ArticleComment;
import jado.model.Board;
import jado.model.Shop;
import jado.service.ArticleService;
import jado.service.ShopService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.exception.ForignKeyException;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private ArticleService articleService;
	@Autowired 
	private ShopService shopService;

	//블로그 페이지에서 board명 클릭시 article list 보여줌 
	@RequestMapping(value="/{shopUrl}/{boardId}", method = RequestMethod.GET)
	public String doGet(Model model, @PathVariable("boardId")String boardId, @PathVariable("shopUrl")String url)
			throws ServletException, IOException {
		Shop shop = shopService.settingByUrl(url);
		model.addAttribute("shop", shop);
		
		Board board = articleService.getBoard(Integer.parseInt(boardId));
		List<Article> articles = articleService.getArticles(Integer.parseInt(boardId));
		model.addAttribute("articles", articles );
		model.addAttribute("board", board);
		return "board";
	}

	//글쓰기 클릭시 board form 페이지로 이동  
	@RequestMapping(value = "/write/{shopUrl}/{boardId}", method = RequestMethod.GET)
	public String wirteGet(Model model, @PathVariable("boardId")String boardId, @PathVariable("shopUrl")String url)
			throws ServletException, IOException {
		Shop shop = shopService.settingByUrl(url);
		model.addAttribute("shop", shop);
		
		Board board = articleService.getBoard(Integer.parseInt(boardId));
		model.addAttribute("board", board);
//		model.addAttribute("boardId", boardId);
		return "boardForm";
	}

	// board에서 쓴 내용 post로 받아오기 
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	protected String writePost(String shopUrl, String boardId, String title, String content,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
		logger.debug("shop data3 {}", title);
		logger.debug("shop data4 {}", content);
		logger.debug("shop data555 {}", boardId);

		Article article = new Article(Integer.parseInt(boardId), title, content);
		articleService.insertArticle(article);
		return "redirect:/board/"+shopUrl+"/"+boardId;
	}
	
	//list에서 title 클릭시 해당 글 보여주는 코드 
	@RequestMapping(value="/show/{shopUrl}/{boardId}/{articleId}", method = RequestMethod.GET)
	public String listGet(Model model, @PathVariable("articleId")String articleId, @PathVariable("boardId")String boardId, @PathVariable("shopUrl")String url)
			throws ServletException, IOException {
		Shop shop = shopService.settingByUrl(url);
		model.addAttribute("shop", shop);
		
		Board board = articleService.getBoard(Integer.parseInt(boardId));
		Article article = articleService.getArticle(Integer.parseInt(articleId));
		List<ArticleComment> comments = articleService.getComments(Integer.parseInt(articleId));
		model.addAttribute("board", board);
		model.addAttribute("article", article);
		model.addAttribute("comments", comments);

		return "showArticle";
	}
	
	//댓글 등록 구현
	/*
	 * 스프링에서 @ModelAttribute를 사용하여 관련 파라미터 값을 객체로 받아올 수 있습니다
	 *  - @see jado.controller.BoardControllerMockTest.commentPost_S_ModelAttribute로_값이_잘_받아지는지() 
	 *  - @ModelAttribute 지정된 객체의 필드와 파라미터 이름을 맞추어 값을 저장하고 객체로 전달한다.
	 */
	@RequestMapping(value = "/answer/save", method = RequestMethod.POST)
	protected String commentPost(String shopUrl,  String boardId, @ModelAttribute ArticleComment articleComment, HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
		logger.debug("articleComment {}", articleComment);
		logger.debug("a {}", boardId);
		
		articleService.insertArticleCommnet(articleComment);
		
		return "redirect:/board/show/"+shopUrl+"/"+boardId+"/"+articleComment.getArticleId();
	}
	
	//댓글 삭제 구현
	/*
	 * 전달되는 파라미터가 문자열 숫자라고 하더라도 스프링은 적절한 타입으로 변경해주는 기능이 있습니다.
	 * protected String commentDeletePost(String shopUrl, String boardId, String articleId, String userId, String commentTime, HttpSession session, Model model) throws ServletException, IOException, ForignKeyException {
	 */
	@RequestMapping(value = "/answer/delete", method = RequestMethod.POST)
	protected String commentDeletePost(String shopUrl, String boardId, int articleId, String userId, String commentTime, HttpSession session, Model model) throws ServletException, IOException, ForignKeyException {
		logger.debug("data {}", articleId);
		logger.debug("data {}", userId);
		logger.debug("data {}", commentTime);
		
		articleService.deleteArticleComment(articleId, userId, commentTime);
		
		return "redirect:/board/show/"+shopUrl+"/"+boardId+"/"+articleId;
	}
	
	// 본문 수정 구현 1 - 글 수정 버튼 클릭시 updateBoardForm 페이지로 이동 
	@RequestMapping(value = "/update/{shopUrl}/{boardId}/{articleId}", method = RequestMethod.GET)
	public String updateGet(Model model, @PathVariable("boardId")String boardId, @PathVariable("articleId")String articleId, @PathVariable("shopUrl")String url)
			throws ServletException, IOException {
		Shop shop = shopService.settingByUrl(url);
		model.addAttribute("shop", shop);
		
		Board board = articleService.getBoard(Integer.parseInt(boardId));
		Article article = articleService.getArticle(Integer.parseInt(articleId));
		model.addAttribute("board", board);
		model.addAttribute("article", article);
//		model.addAttribute("boardId", boardId);
		return "updateBoardForm";
	}
	
	//article 본문 수정 구현 2 - updateBoardForm에서 쓴 내용 받아오기  
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	protected String articleUpdatePost(String shopUrl, String articleId, String boardId, String title, String content,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
		Article article = new Article(title, content);
		article.setId(Integer.parseInt(articleId));
		articleService.updateArticle(article);
		
		return "redirect:/board/show/"+shopUrl+"/"+boardId+"/"+articleId;
	}
	
	//article 본문 삭제 구현 
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	protected String articleDeletePost(String shopUrl, String articleId, String boardId,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
		logger.debug("boardId {}", boardId);
		articleService.deleteArticle(Integer.parseInt(articleId));
		
		return "redirect:/board/"+shopUrl+"/"+boardId;
	}
}
