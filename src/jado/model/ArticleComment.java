package jado.model;

import java.sql.Timestamp;

public class ArticleComment {
	private int articleId;
	private String userId;
	private Timestamp commentTime;
	private String content;

	public ArticleComment() {
	}

	public ArticleComment(int articleId, String userId, Timestamp commentTime, String content) {
		super();
		this.articleId = articleId;
		this.userId = userId;
		this.commentTime = commentTime;
		this.content = content;
	}

	public ArticleComment(int articleId, String userId, String content) {
		this(articleId, userId, null, content);
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArticleComment [articleId=" + articleId + ", userId=" + userId + ", commentTime=" + commentTime + ", content=" + content + "]";
	}

}
