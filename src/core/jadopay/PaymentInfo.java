package core.jadopay;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class PaymentInfo {

	@NotEmpty(message="Shop Url은 공백일 수 없습니다")
	@Size(min = 1, max = 45, message="Shop Url의 길이는 최소 {2}자에서 최대 {1}자 이어야 합니다")
	private String shopUrl;

	@Range(min = 1, max = 9999999)
	private int productId;
	
	@Range(min = 1, max = 1000)
	private int productAmount;

	public PaymentInfo() {
	}

	public PaymentInfo(String shopUrl, Integer productId, Integer productAmount) {
		super();
		this.shopUrl = shopUrl;
		initProductIdFieldWithNullCheck(productId);
		initProductAmountFieldWithNullCheck(productAmount);
	}

	private void initProductIdFieldWithNullCheck(Integer productId) {
		if(productId == null) {
			this.productId = -1;
		} else {
			this.productId = productId;
		}
	}
	
	private void initProductAmountFieldWithNullCheck(Integer productAmount) {
		if(productAmount == null) {
			this.productAmount = -1;
		} else {
			this.productAmount = productAmount;
		}
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	@Override
	public String toString() {
		return "PaymentInfo [shopUrl=" + shopUrl + ", productId=" + productId + "]";
	}
	
}
