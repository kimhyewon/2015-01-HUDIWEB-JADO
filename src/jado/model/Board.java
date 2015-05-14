package jado.model;


public class Board {
	private int id;
	private String shopUrl;
	private String name;

	public Board() {
	}

	public Board(int id, String shopUrl, String name) {
		super();
		this.id = id;
		this.shopUrl = shopUrl;
		this.name = name;
	}

	public Board(String shopUrl, String name) {
		this(0, shopUrl, name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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


}
