package jado.model;

public class Board {

	private String shopUrl;
	private String name;
	
	
	public Board(String shopUrl, String name) {
		super();
		this.shopUrl = shopUrl;
		this.name = name;
	}
	
	
	public String getShopUrl() {
		return shopUrl;
	}
	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return "Board [shopUrl=" + shopUrl + ", name=" + name + "]";
	}
	
	
	
	

}
