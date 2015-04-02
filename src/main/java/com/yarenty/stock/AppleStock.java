/**
 * 
 */
package com.yarenty.stock;

import java.math.BigDecimal;

/**
 * @author yarenty
 *
 */
public class AppleStock implements Stock {

	
	private BigDecimal price;
	
	AppleStock(BigDecimal price) {
		this.price = price;
	}
	
	/* (non-Javadoc)
	 * @see com.yarenty.stock.Stock#getPrice()
	 */
	public BigDecimal getPrice() {
		return price;
	}

}
