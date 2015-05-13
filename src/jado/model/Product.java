package jado.model;

import java.sql.Timestamp;
import java.util.List;

public class Product {
	private int id;
	private int categoryId;
	private String name;
	private int price;
	private int stock;
	private String imgUrl;
	private String desc;
	private Timestamp insertTime;
	private List<ProductComment> comments;

	public Product(){ 
	}
	
	public Product(int id, int categoryId, String name, int price, int stock, String imgUrl, String desc, Timestamp insertTime) {
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

	public Product(String name, int price, int stock, String imgUrl, String desc) {
		this(0, 0, name, price, stock, imgUrl, desc, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public List<ProductComment> getComments() {
		return comments;
	}

	public void setComments(List<ProductComment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", price=" + price + ", stock=" + stock + ", imgUrl=" + imgUrl + ", desc=" + desc + ", insertTime=" + insertTime
				+ ", comments=" + comments + "]";
	}

}
