package jado.model;

import java.sql.Timestamp;
import java.util.List;

public class Article {
	
	/*
	 * 물론 테스트시는 default로 해야하지만..
	 * 특별한 사유가 없으면 private으로 해주세요 !!
	 */
	String shopUrl;
	String boardName;
	String title;
	String content;
	Timestamp articleTime;
	List<ArticleComment> comments = null;
	
	public Article() { }

	public Article(String shopUrl, String boardName, String title, String content, Timestamp articleTime, List<ArticleComment> comments) {
		super();
		this.shopUrl = shopUrl;
		this.boardName = boardName;
		this.title = title;
		this.content = content;
		this.articleTime = articleTime;
		this.comments = comments;
		toString();
	}

	public Article(String shopUrl, String boardName, String title, String content, Timestamp articleTime) {
		this(shopUrl, boardName, title, content, articleTime, null);
	}

	public Article(String shopUrl, String boardName, String title, String content) {
		this(shopUrl, boardName, title, content, null, null);
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getArticleTime() {
		return articleTime;
	}

	public void setArticleTime(Timestamp articleTime) {
		this.articleTime = articleTime;
	}

	public List<ArticleComment> getComments() {
		return comments;
	}

	public void setComments(List<ArticleComment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Article [shopUrl=" + shopUrl + ", boardName=" + boardName + ", title=" + title + ", content=" + content + ", articleTime=" + articleTime + ", comments=" + comments + "]";
	}

	public boolean update(Article temp) {
		boolean result = false;
		if (this.content.equals(temp.content)) {
			this.content = temp.content;
			result = true;
		}
		return result;
	}

	

}
