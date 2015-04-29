package jado.controller;

import jado.dao.ArticleDao;
import jado.model.Article;
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
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value="/{boardId}", method = RequestMethod.GET)
	public String doGet(Model model, @PathVariable("boardId")String boardId)
			throws ServletException, IOException {
		Board board = articleService.getBoard(Integer.parseInt(boardId));
		List<Article> articles = articleService.getArticles(Integer.parseInt(boardId));
		model.addAttribute("articles", articles );
		model.addAttribute("board", board);
		return "board";
	}

	@RequestMapping(value = "/write/{boardId}", method = RequestMethod.GET)
	public String wirteGet(@PathVariable("boardId")String boardId, Model model)
			throws ServletException, IOException {
		model.addAttribute("boardId", boardId);
		return "boardForm";
	}

	// TODO - board에서 쓴 내용 post로 받아오기 구현 해야함
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	protected String writePost(String boardId, String title, String content,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
		logger.debug("shop data3 {}", title);
		logger.debug("shop data4 {}", content);

		Article article = new Article(Integer.parseInt(boardId), title, content);
		articleService.insertArticle(article);
		return "board";

	}
}
