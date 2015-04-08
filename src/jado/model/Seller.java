package jado.model;

public class Seller extends User {
	private String url;
	private String bank;
	private String bankAccount;

	public Seller() { }

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

	public void setUrl(String url) {
		this.url = url;
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
		return "Seller [url=" + url + ", bank=" + bank + ", bankAccount=" + bankAccount + "]";
	}

}
