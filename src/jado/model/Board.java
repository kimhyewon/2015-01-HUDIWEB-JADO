package jado.model;

import java.util.List;

public class Board {

	private String shopUrl;
	private String name;
	private List<Article> articles;

	public Board() {
	}

	public Board(String shopUrl, String name, List<Article> articles) {
		super();
		this.shopUrl = shopUrl;
		this.name = name;
		this.articles = articles;
	}

	public Board(String shopUrl, String name) {
		this(shopUrl, name, null);
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Board [shopUrl=" + shopUrl + ", name=" + name + ", articles=" + articles + "]";
	}

}
