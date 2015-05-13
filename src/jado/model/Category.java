package jado.model;

import java.util.List;

public class Category {
	private int id;
	private String name;
	private String shopUrl;
	private List<Product> products;

	public Category() {
	}
	
	public Category(int id, String name, String shopUrl, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.shopUrl = shopUrl;
		this.products = products;
	}

	public Category(String name, String shopUrl) {
		this(0, name, shopUrl, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", shopUrl=" + shopUrl + ", products=" + products + "]";
	}

}
