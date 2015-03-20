package jado.model;

public class Seller extends User {
	private String shopUrl;
	private String shopPhone;
	private String bank;
	private String bankAccount;
	
	//Constructor
	public Seller(String userId, String shopUrl, String shopPhone, String bank, String bankAccount) {
		super(userId);
		this.shopUrl = shopUrl;
		this.shopPhone = shopPhone;
		this.bank = bank;
		this.bankAccount = bankAccount;
	}
	
	//Getter
	public String getShopUrl() {
		return shopUrl;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public String getBank() {
		return bank;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	
	//Setter
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
}
