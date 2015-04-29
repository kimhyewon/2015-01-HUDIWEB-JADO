package jado.model;

import java.sql.Timestamp;
import java.util.List;

public class Article {

	private int id;
	private int boardId;
	private String title;
	private String content;
	private Timestamp articleTime;
	private List<ArticleComment> comments = null;

	public Article() {
	}

	public Article(int id, int boardId, String title, String content, Timestamp articleTime) {
		super();
		this.id = id;
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.articleTime = articleTime;
	}

	public Article(int boardId, String title, String content) {
		this(0, boardId, title, content, null);
	}
	public Article(String title, String content) {
		this(0, 0, title, content, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
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
		return "Article [id=" + id + ", boardId=" + boardId + ", title=" + title + ", content=" + content + ", articleTime=" + articleTime + ", comments=" + comments + "]";
	}

	public boolean update(Article temp) {
		boolean result = false;
		if (!this.title.equals(temp.title)) {
			this.title = temp.title;
			result = true;
		}
		if (!this.content.equals(temp.content)) {
			this.content = temp.content;
			result = true;
		}
		return result;
	}

}
