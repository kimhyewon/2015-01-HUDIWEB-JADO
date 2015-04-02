package jado.model;

public class Seller extends User {
	private String url;
	private String bank;
	private String bankAccount;

	public Seller(String userId, String url, String bank, String bankAccount) {
		super(userId);
		this.url = url;
		this.bank = bank;
		this.bankAccount = bankAccount;
	}

	public String getUrl() {
		return url;
	}

	public String getBank() {
		return bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public boolean update(Seller seller) {
		boolean result = false;
		if (this.bank.equals(seller.bank)) {
			result = true;
		}
		if (this.bankAccount.equals(seller.bankAccount)) {
			result = true;
		}
		return result;
	}

}
