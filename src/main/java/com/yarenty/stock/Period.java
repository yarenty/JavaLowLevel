package com.yarenty.stock;


import java.math.BigDecimal;

public interface Period extends Comparable<Period> {

    public int getBuyTime();

    public int getSellTime();

    public BigDecimal getBuyPrice();

    public BigDecimal getSellPrice();

    public void setBuy(int buyTime, BigDecimal buyPrice);

    public void setSell(int sellTime, BigDecimal sellPrice);

}
