package jado.model;

import java.util.List;

public class Category {
	private int id;
	private String name;
	private String shopUrl;
	private List<Product> products;

	public Category(int id, String name, String shopUrl, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.shopUrl = shopUrl;
		this.products = products;
	}
	

	public Category(int id, String name, String shopUrl) {
		this(id, name, shopUrl, null);
	}


	public Category(String name, String shopUrl) {
		this(0, name, shopUrl, null);
	}

	public String getName() {
		return name;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", shopUrl=" + shopUrl + ", products=" + products + "]";
	}

}
