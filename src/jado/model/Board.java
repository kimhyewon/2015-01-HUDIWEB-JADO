package jado.model;

import java.util.List;

public class Board {
	String url;
	String name;
	List<Article> articles;
	
	public Board(String url, String name, List<Article> articles) {
		super();
		this.url = url;
		this.name = name;
		this.articles = articles;
	}

	public Board(String url, String name) {
		this(url, name, null);
	}

	
}
