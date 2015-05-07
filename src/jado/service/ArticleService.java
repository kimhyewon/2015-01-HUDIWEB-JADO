package jado.service;

import java.util.List;

import jado.dao.ArticleCommentDao;
import jado.dao.ArticleDao;
import jado.dao.BoardDao;
import jado.model.Article;
import jado.model.ArticleComment;
import jado.model.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.exception.ForignKeyException;

@Service
public class ArticleService {
	@Autowired private ArticleDao articleDao;
	@Autowired private BoardDao boardDao;
	@Autowired private ArticleCommentDao articleCommentDao;

	public void insertArticle(Article article) throws ForignKeyException {
		Board board = boardDao.selectByPk(article.getBoardId());
		if (board == null) {
			throw new ForignKeyException("잘못된 경로로 접근하셨습니다.");
		}
		articleDao.insert(article);
	}

	public List<Article> getArticles(int boardId) {
		return articleDao.selectAllByBoard(boardId);
	}

	public Board getBoard(int boardId) {
		return boardDao.selectByPk(boardId);
	}
	
	public Article getArticle(int articleId) {
		return articleDao.selectByPk(articleId);
	}
	
	public List<ArticleComment> getComments(int articleId) {
		return articleCommentDao.findByArticle(articleId);
	}
	
	public void insertArticleCommnet(ArticleComment articleComment) throws ForignKeyException {
		Article article = articleDao.selectByPk(articleComment.getArticleId());
		if (article == null) {
			throw new ForignKeyException("잘못된 경로로 접근하셨습니다.");
		}
		articleCommentDao.insert(articleComment);
	}
	
	public void deleteArticleComment(int articleId, String userId, String commentTime) throws ForignKeyException {
		articleCommentDao.remove(articleId, userId, commentTime);
	}
	
	public void deleteArticle(int articleId) throws ForignKeyException {
		articleDao.remove(articleId);
	}
}
