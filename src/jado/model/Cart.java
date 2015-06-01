package jado.model;


public class Cart {
	private String shopUrl;
	private String customerId;
	private int productId;
	
	private int amount;
	private int isPay;
	private String cartTime;
	public String getShopUrl() {
		return shopUrl;
	}
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getIsPay() {
		return isPay;
	}
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}
	public String getCartTime() {
		return cartTime;
	}
	public void setCartTime(String cartTime) {
		this.cartTime = cartTime;
	}

	
	
}