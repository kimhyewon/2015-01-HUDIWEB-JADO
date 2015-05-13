package jado.model;

public class Seller extends User {
	private String shopUrl;
	private String bank;
	private String bankAccount;

	public Seller() {
	}

	public Seller(String userId, String shopUrl, String bank, String bankAccount) {
		super(userId);
		this.shopUrl = shopUrl;
		this.bank = bank;
		this.bankAccount = bankAccount;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public String getBank() {
		return bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public boolean update(Seller seller) {
		boolean result = false;
		if (!this.bank.equals(seller.bank)) {
			this.bank = seller.bank;
			result = true;
		}
		if (!this.bankAccount.equals(seller.bankAccount)) {
			this.bankAccount = seller.bankAccount;
			result = true;
		}
		return result;
	}

	@Override
	public String toString() {
		return "Seller [url=" + shopUrl + ", bank=" + bank + ", bankAccount=" + bankAccount + "]";
	}

}
