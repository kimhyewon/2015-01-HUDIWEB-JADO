package jado.model;

import java.util.List;

public class Article {
	String shopUrl;
	String boardName;
	String title;
	String content;
	String articleTime;
	List<ArticleComment> comment = null;

	public Article(String shopUrl, String boardName, String title, String content, String articleTime, List<ArticleComment> comment) {
		super();
		this.shopUrl = shopUrl;
		this.boardName = boardName;
		this.title = title;
		this.content = content;
		this.articleTime = articleTime;
		this.comment = comment;
		toString();
	}

	public Article(String shopUrl, String boardName, String title, String content, String articleTime) {
		this(shopUrl, boardName, title, content, articleTime, null);
	}

	public Article(String shopUrl, String boardName, String title, String content) {
		this(shopUrl, boardName, title, content, null, null);
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public String getBoardName() {
		return boardName;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Article [shopUrl=" + shopUrl + ", boardName=" + boardName + ", title=" + title + ", content=" + content + ", articleTime=" + articleTime + ", comment=" + comment + "]";
	}

}
