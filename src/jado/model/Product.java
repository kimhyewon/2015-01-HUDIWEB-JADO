package jado.model;

import core.jdbc.PreparedStatementSetter;

public class Product {
	private int id;
	private int categoryId;
	private String name;
	private int price;
	private int stock;
	private String imgUrl;
	private String desc;
	private String insertTime;

	public Product(int id, int categoryId, String name, int price, int stock, String imgUrl, String desc, String insertTime) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.imgUrl = imgUrl;
		this.desc = desc;
		this.insertTime = insertTime;
	}

	public Product(int categoryId, String name, int price, int stock, String imgUrl, String desc) {
		this(0, categoryId, name, price, stock, imgUrl, desc, null);
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getDesc() {
		return desc;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", price=" + price + ", stock=" + stock + ", imgUrl=" + imgUrl + ", desc=" + desc + ", insertTime=" + insertTime
				+ "]";
	}

}
