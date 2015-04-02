/**
 * 
 */
package com.yarenty.stock;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yarenty
 *
 */
public class AppleStockAnalyzer implements StockAnalyzer {

	/** 
	 * @see com.yarenty.stock.StockAnalyzer#bestProfitPeriod(java.util.List)
	 */
	public Period bestProfitPeriod(final List<Stock> stocks) {

		final Period bestPeriod = new PeriodImpl(0, stocks.get(0).getPrice());
		final Period currentPeriod = new PeriodImpl(0, stocks.get(0).getPrice());
		
		int currentTime = 0;
		BigDecimal currentPrice;
		
		boolean goingUP = true;
		
		for (final Stock stock:stocks){
			currentPrice = stock.getPrice();
			
			if (currentPrice.compareTo(currentPeriod.getBuyPrice()) < 0) {
				//what to do if is less than min
				
				if (goingUP) { //only on first time going under
								// we want to setup new period
								// and register last one as best (if it was)
					updateBestPeriod(bestPeriod,currentPeriod);
					goingUP = false;
				} 
				
				//clean for next period
				currentPeriod.setBuy(currentTime, currentPrice);
				currentPeriod.setSell(currentTime, currentPrice);
				
			} else {
				goingUP = true;
				
				if (currentPrice.compareTo(currentPeriod.getSellPrice()) > 0) {
					//what to do if is more than max
					
					// thats actually ok
					// simply change sell to new one
					
					currentPeriod.setSell(currentTime,currentPrice);
				}
			}
			//between --- dont care ...
			
			currentTime++;
		}
		
		//final check 
		return updateBestPeriod(bestPeriod,currentPeriod);
	}

	

	/**
	 * updating best period
	 * @param bestPeriod
	 * @param currentPeriod
	 * @return
	 */
	private Period updateBestPeriod(final Period bestPeriod, final Period currentPeriod) {
		if (currentPeriod.compareTo(bestPeriod) > 0) {
			bestPeriod.setBuy(currentPeriod.getBuyTime(), currentPeriod.getBuyPrice());
			bestPeriod.setSell(currentPeriod.getSellTime(), currentPeriod.getSellPrice());
		}
		return bestPeriod;
	}



	
}
