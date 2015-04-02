/**
 * 
 */
package com.yarenty.stock;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yarenty
 *
 */
public class StockAnalyzerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void givenStocks_whenPricesAllwaysDown_thenReturnSinglePoint() {
		List<Stock> stocks = new ArrayList<Stock>();
		for (int i = 0; i<100; i++) {
			stocks.add(new AppleStock(BigDecimal.valueOf(500.0-i)));
		}

		Period p= new AppleStockAnalyzer().bestProfitPeriod(stocks);
		
		assertEquals ( p.getBuyTime(), p.getSellTime());
		assertEquals ( 0, p.getBuyTime());
		
	}

	
	@Test
	public final void givenStocks_whenPricesAllwaysUp_thenReturnMinandMax() {
		List<Stock> stocks = new ArrayList<Stock>();
		for (int i = 0; i<100; i++) {
			stocks.add(new AppleStock(BigDecimal.valueOf(500.0+i)));
		}

		Period p= new AppleStockAnalyzer().bestProfitPeriod(stocks);
		
		assertEquals ( 0, p.getBuyTime());
		assertEquals ( 99, p.getSellTime());
		
	}
	
	
	@Test
	public final void givenStocks_whenPricesDownThenUp_thenReturnMinDownAndThenUp() {
		List<Stock> stocks = new ArrayList<Stock>();
		for (int i = 0; i<60; i++) {
			stocks.add(new AppleStock(BigDecimal.valueOf(500.0-i)));
		}
		
		for (int i = 0; i<40; i++) {
			stocks.add(new AppleStock(BigDecimal.valueOf(440.0+i)));
		}

		Period p= new AppleStockAnalyzer().bestProfitPeriod(stocks);
		
		assertEquals ( 60, p.getBuyTime());
		assertEquals ( 99, p.getSellTime());
	}
	
	
	@Test
	public final void givenStocks_whenPricesUpDownUpDown_thenReturnM() {
		List<Stock> stocks = new ArrayList<Stock>();
		double value = 500.0;
		for (int i = 0; i<10; i++) {
			value++;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<5; i++) {
			value--;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<10; i++) {
			value++;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<5; i++) {
			value--;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<10; i++) {
			value++;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<5; i++) {
			value--;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<10; i++) {
			value++;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<40; i++) {
			value--;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}

		Period p= new AppleStockAnalyzer().bestProfitPeriod(stocks);
		
		assertEquals ( 0, p.getBuyTime());
		assertEquals ( 54, p.getSellTime());
	}
	
	
	@Test
	public final void givenStocks_whenPricesUpBigDownBigUp_thenReturnBigs() {
		List<Stock> stocks = new ArrayList<Stock>();
		double value = 500.0;
		for (int i = 0; i<10; i++) {
			value++;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<5; i++) {
			value--;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<10; i++) {
			value++;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<5; i++) {
			value = value-10;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}
		for (int i = 0; i<40; i++) {
			value++;
			stocks.add(new AppleStock(BigDecimal.valueOf(value)));
		}

		Period p= new AppleStockAnalyzer().bestProfitPeriod(stocks);
		
		assertEquals ( 29, p.getBuyTime());
		assertEquals ( 69, p.getSellTime());
	}
	
	
}
