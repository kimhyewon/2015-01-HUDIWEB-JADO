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
import core.exception.InsertTargetRecordNotFoundException;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private ArticleCommentDao articleCommentDao;

	public void insertArticle(Article article) throws InsertTargetRecordNotFoundException {
		Board board = boardDao.selectByPk(article.getBoardId());
		if (board == null) {
			throw new InsertTargetRecordNotFoundException("게시물 등록 대상 게시판이 존재하지 않습니다.");
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

	public void insertArticleCommnet(ArticleComment articleComment) throws InsertTargetRecordNotFoundException {
		Article article = articleDao.selectByPk(articleComment.getArticleId());
		if (article == null) {
			throw new InsertTargetRecordNotFoundException("댓글 등록 대상 게시물이 존재하지 않습니다.");
		}
		articleCommentDao.insert(articleComment);
	}

	public void deleteArticleComment(int articleId, String userId, String commentTime) {
		articleCommentDao.remove(articleId, userId, commentTime);
	}

	public void deleteArticle(int articleId) {
		List<ArticleComment> comments = articleCommentDao.findByArticle(articleId);
		for (ArticleComment articleComment : comments) {
			if(!articleComment.getUserId().equals(articleId)) {
				throw new ForignKeyException("다른 사람의 댓글이 있어 지울수 없습니다.");
			}
		}
		articleDao.remove(articleId);
	}

	public void updateArticle(Article article) {
		articleDao.update(article);
	}

	public void deleteArticleComment(ArticleComment articleComment) {
		articleCommentDao.removeByPk(articleComment);
	}
}
