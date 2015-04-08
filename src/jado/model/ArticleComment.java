package jado.model;

//import java.util.Date;
import java.sql.Timestamp;

public class ArticleComment {
	private String shopUrl;
	private String articleTitle;
	private String boardName;
	private String userId;
	// private Date commentTime;
	private Timestamp commentTime;
	private String content;

	public ArticleComment() {
	}

	public ArticleComment(String shopUrl, String articleTitle, String boardName, String userId, Timestamp timestamp, String content) {
		super();
		this.shopUrl = shopUrl;
		this.articleTitle = articleTitle;
		this.boardName = boardName;
		this.userId = userId;
		this.commentTime = timestamp;
		this.content = content;
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

	public Timestamp getCommentTime() {
		return commentTime;
	}

	public String getContent() {
		return content;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArticleComment [shopUrl=" + shopUrl + ", articleTitle=" + articleTitle + ", boardName=" + boardName + ", userId=" + userId + ", commentTime=" + commentTime + ", content=" + content
				+ "]";
	}
}
