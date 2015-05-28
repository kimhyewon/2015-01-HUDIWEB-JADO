package jado.controller;

import jado.model.ArticleComment;
import jado.service.ArticleService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.exception.InsertTargetRecordNotFoundException;

@RestController
@RequestMapping(value = "/api/article")
public class ArticleCommentController {
	private static final Logger logger = LoggerFactory.getLogger(ArticleCommentController.class);

	@Autowired
	ArticleService articleService;

	// 글에 댓글 등록 구현
	@RequestMapping(value = "/comment/create", method = RequestMethod.POST)
	public List<ArticleComment> commentPost(ArticleComment articleComment) {
		try {
			articleService.insertArticleCommnet(articleComment);
		} catch (InsertTargetRecordNotFoundException e) {
			logger.debug("failed {}", e.getMessage());
			return null;
		}
		return articleService.getComments(articleComment.getArticleId());
	}
	
	// 글에 댓글 삭제 구현
	@RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
	public List<ArticleComment> commentDeletePost(ArticleComment articleComment) {
		try {
			articleService.deleteArticleComment(articleComment);
		} catch (Exception e) {
			logger.debug("failed"+e.getMessage());
			return null;
		}
		return articleService.getComments(articleComment.getArticleId());
	}
}
