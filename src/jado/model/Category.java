package jado.model;

import java.util.List;

public class Category {
	private int id;
	private String name;
	private String shop_url;
	private List<Product> products;
	
	public Category(int id, String name, String shop_url, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.shop_url = shop_url;
		this.products = products;
	}

	public Category(String name, String shop_url) {
		this(0, name, shop_url, null);
	}
	
	
	
	
}
