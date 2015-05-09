package jado.controller;

import jado.model.Article;
import jado.model.ArticleComment;
import jado.model.Board;
import jado.service.ArticleService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.exception.ForignKeyException;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private ArticleService articleService;

	//블로그 페이지에서 board명 클릭시 article list 보여줌 
	@RequestMapping(value="/{boardId}", method = RequestMethod.GET)
	public String doGet(Model model, @PathVariable("boardId")String boardId)
			throws ServletException, IOException {
		Board board = articleService.getBoard(Integer.parseInt(boardId));
		List<Article> articles = articleService.getArticles(Integer.parseInt(boardId));
		model.addAttribute("articles", articles );
		model.addAttribute("board", board);
		return "board";
	}

	//글쓰기 클릭시 board form 페이지로 이동  
	@RequestMapping(value = "/write/{boardId}", method = RequestMethod.GET)
	public String wirteGet(Model model, @PathVariable("boardId")String boardId)
			throws ServletException, IOException {
		Board board = articleService.getBoard(Integer.parseInt(boardId));
		model.addAttribute("board", board);
//		model.addAttribute("boardId", boardId);
		return "boardForm";
	}

	// board에서 쓴 내용 post로 받아오기 
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	protected String writePost(String boardId, String title, String content,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
		logger.debug("shop data3 {}", title);
		logger.debug("shop data4 {}", content);
		logger.debug("shop data555 {}", boardId);

		Article article = new Article(Integer.parseInt(boardId), title, content);
		articleService.insertArticle(article);
		return "redirect:/board/"+boardId;
	}
	
	//list에서 title 클릭시 해당 글 보여주는 코드 
	@RequestMapping(value="/show/{boardId}/{articleId}", method = RequestMethod.GET)
	public String listGet(Model model, @PathVariable("articleId")String articleId, @PathVariable("boardId")String boardId)
			throws ServletException, IOException {
		Board board = articleService.getBoard(Integer.parseInt(boardId));
		Article article = articleService.getArticle(Integer.parseInt(articleId));
		List<ArticleComment> comments = articleService.getComments(Integer.parseInt(articleId));
		model.addAttribute("board", board);
		model.addAttribute("article", article);
		model.addAttribute("comments", comments);

		return "showArticle";
	}
	
	//댓글 등록 구현
	@RequestMapping(value = "/answer/save", method = RequestMethod.POST)
	protected String commentPost(String boardId, String articleId, String userId, String content,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
		logger.debug("shop data3 {}", articleId);
		logger.debug("shop data3 {}", userId);
		logger.debug("shop data4 {}", content);
		logger.debug("a {}", boardId);
		
		ArticleComment articleComment = new ArticleComment(Integer.parseInt(articleId), userId, content);
		articleService.insertArticleCommnet(articleComment);
		
		return "redirect:/board/show/"+boardId+"/"+articleId;
	}
	
	//댓글 삭제 구현
	@RequestMapping(value = "/answer/delete", method = RequestMethod.POST)
	protected String commentDeletePost(String boardId, String articleId, String userId, String commentTime,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
		logger.debug("data {}", articleId);
		logger.debug("data {}", userId);
		logger.debug("data {}", commentTime);
		
		articleService.deleteArticleComment(Integer.parseInt(articleId), userId, commentTime);
		
		return "redirect:/board/show/"+boardId+"/"+articleId;
	}
	
	// 본문 수정 구현 1 - 글 수정 버튼 클릭시 updateBoardForm 페이지로 이동 
	@RequestMapping(value = "/update/{boardId}/{articleId}", method = RequestMethod.GET)
	public String updateGet(Model model, @PathVariable("boardId")String boardId, @PathVariable("articleId")String articleId)
			throws ServletException, IOException {
		Board board = articleService.getBoard(Integer.parseInt(boardId));
		Article article = articleService.getArticle(Integer.parseInt(articleId));
		model.addAttribute("board", board);
		model.addAttribute("article", article);
//		model.addAttribute("boardId", boardId);
		return "updateBoardForm";
	}
	
	//article 본문 수정 구현 2 - updateBoardForm에서 쓴 내용 받아오기  
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	protected String articleUpdatePost(String articleId, String boardId, String title, String content,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
		Article article = new Article(title, content);
		article.setId(Integer.parseInt(articleId));
		articleService.updateArticle(article);
		
		return "redirect:/board/show/"+boardId+"/"+articleId;
	}
	
	//article 본문 삭제 구현 
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	protected String articleDeletePost(String articleId, String boardId,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
		logger.debug("boardId {}", boardId);
		articleService.deleteArticle(Integer.parseInt(articleId));
		
		return "redirect:/board/"+boardId;
	}
}
