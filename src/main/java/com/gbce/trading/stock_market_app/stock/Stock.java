package com.gbce.trading.stock_market_app.stock;

import java.util.List;
import com.gbce.stock_market_app.trading.trade.Trade;

public interface Stock 
{
	public String getSymbol();
	public int getParValue();
	public int getLastDividend();
	public double dividendYield(int price);
	public double priceToEarningsRatio(int price);
	public void addTrade(Trade trade);
	public List<Trade> getTrades();	
}
