package jado.model;

public class ProductComment {
	private int productId;
	private String userId;
	private String commentTime;
	private String content;

	public ProductComment() { }
	public ProductComment(int productId, String userId, String commentTime, String content) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.commentTime = commentTime;
		this.content = content;
	}

	public ProductComment(int productId, String userId, String content) {
		this(productId, userId, null, content);
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
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
		return "ProductComment [productId=" + productId + ", userId=" + userId + ", commentTime=" + commentTime + ", content=" + content + "]";
	}

}
