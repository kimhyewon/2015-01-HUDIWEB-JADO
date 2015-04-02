package jado.model;

import java.util.List;

public class Board {

	private String shopUrl;
	private String name;
	private List<Article> articles;

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

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Board [shopUrl=" + shopUrl + ", name=" + name + ", articles=" + articles + "]";
	}

}
