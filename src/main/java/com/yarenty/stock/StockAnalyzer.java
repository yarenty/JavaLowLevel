package com.yarenty.stock;


import java.util.List;

/**
 * @author yarenty
 */
public interface StockAnalyzer {

    Period bestProfitPeriod(List<Stock> stocks);
}
