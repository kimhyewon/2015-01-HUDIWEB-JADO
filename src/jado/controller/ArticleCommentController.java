package jado.controller;

import jado.model.ArticleComment;
import jado.model.Notice;
import jado.service.ArticleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import core.exception.InsertTargetRecordNotFoundException;
import core.util.ModelAndViewUtils;

@Controller
@RequestMapping(value = "/api/article")
public class ArticleCommentController {
	private static final Logger logger = LoggerFactory.getLogger(ArticleCommentController.class);

	@Autowired
	ArticleService articleService;

	// 글에 댓글 등록 구현
	@RequestMapping(value = "/comment/create", method = RequestMethod.POST)
	public ModelAndView commentPost(ArticleComment articleComment) {
		logger.debug("articleComment {}", articleComment.getArticleId());
		try {
			articleService.insertArticleCommnet(articleComment);
		} catch (InsertTargetRecordNotFoundException e) {
			logger.debug("failed {}", e.getMessage());
			return ModelAndViewUtils.renderToNotice(new Notice("Failed", e.getMessage()));
		}
		return new ModelAndView("redirect:/");
	}

	// 글에 댓글 삭제 구현
	@RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
	public ModelAndView commentDeletePost(ArticleComment articleComment) {
		articleService.deleteArticleComment(articleComment);
		return new ModelAndView("redirect:/");
	}
}
