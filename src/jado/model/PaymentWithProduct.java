package jado.model;

import java.sql.Timestamp;

public class PaymentWithProduct extends Product{
	private String Bank;
	private Integer realPrice;
	private Timestamp payTime;
	
	public String getBank() {
		return Bank;
	}
	public void setBank(String bank) {
		Bank = bank;
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
	
	@Override
	public String toString() {
		return "Info["+Bank+":"+realPrice+":"+payTime+"]"+super.toString();
	}
	
	
}
