package jado.model;

import java.sql.Timestamp;

public class PaymentWithProduct extends Product {
	private String bank;
	private Integer realPrice;
	private Timestamp payTime;
	private int amount;

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Integer getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Integer realPrice) {
		this.realPrice = realPrice;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Info[" + bank + ":" + realPrice + ":" + payTime + "]" + super.toString();
	}

	public void setAmount() {
		amount = realPrice/getPrice();
	}

}
