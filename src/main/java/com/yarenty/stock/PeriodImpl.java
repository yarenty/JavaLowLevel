package com.yarenty.stock;

import java.math.BigDecimal;


public class PeriodImpl implements Period {

	private int buyTime;
	private BigDecimal buyPrice;
	private int sellTime;
	private BigDecimal sellPrice;
	
	
	

	public PeriodImpl(int initTime, BigDecimal initPrice) {
		buyTime = initTime;
		sellTime = initTime;
		buyPrice = initPrice;
		sellPrice = initPrice;
		
	}

	
	public int getBuyTime() {
		return buyTime;
	}
	public int getSellTime() {
		return sellTime;
	}


	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	
	
	public void setBuy(int buyTime, BigDecimal buyPrice) {
		this.buyTime = buyTime;
		this.buyPrice = buyPrice;
	}

	public void setSell(int sellTime, BigDecimal sellPrice) {
		this.sellTime = sellTime;
		this.sellPrice = sellPrice;
	}


	public int compareTo(Period o) {
		return this.sellPrice.subtract(this.buyPrice).compareTo(o.getSellPrice().subtract(o.getBuyPrice()));
	}
}
