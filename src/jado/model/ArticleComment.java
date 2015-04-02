package jado.model;

public class ArticleComment {
	private String shopUrl;
	private String articleTitle;
	private String boardName;
	private String userId;
	private String commentTime;
	private String content;

	public ArticleComment(String shopUrl, String articleTitle, String boardName, String userId, String commentTime, String content) {
		super();
		this.shopUrl = shopUrl;
		this.articleTitle = articleTitle;
		this.boardName = boardName;
		this.userId = userId;
		this.commentTime = commentTime;
		this.content = content;

		toString();
	}

	public ArticleComment(String shopUrl, String articleTitle, String boardName, String userId, String content) {
		this(shopUrl, articleTitle, boardName, userId, null, content);
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public String getBoardName() {
		return boardName;
	}

	public String getUserId() {
		return userId;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "ArticleComment [shopUrl=" + shopUrl + ", articleTitle=" + articleTitle + ", boardName=" + boardName + ", userId=" + userId + ", commentTime=" + commentTime + ", content=" + content
				+ "]";
	}

}
