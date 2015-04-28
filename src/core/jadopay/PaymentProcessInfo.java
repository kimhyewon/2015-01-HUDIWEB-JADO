package core.jadopay;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class PaymentProcessInfo extends PaymentInfo {

	@NotNull
	@Size(min = 1, max = 45)
	private String userId;

	@NotNull
	private String bankName;

	@NotNull
	@Range(min = 1, max = 15000000)
	private int price;

	public PaymentProcessInfo() {
	}

	public PaymentProcessInfo(String userId, String shopUrl, int productId, int productAmount, int price) {
		super(shopUrl, productId, productAmount);
		this.userId = userId;
		this.price = price;
	}

	public PaymentProcessInfo(String userId, String shopUrl, int productId, String bankName, int price, int productAmount) {
		this(userId, shopUrl, productId, productAmount, price);
		this.bankName = bankName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PaymentProcessInfo [userId=" + userId + ", bankName=" + bankName + ", price=" + price + "]";
	}
}
