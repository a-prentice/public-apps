package com.gbce.trading.stock_market_app;

import java.time.LocalDateTime;

public interface StockMarket {
	
	public double dividendYield(String symbol, int price);
	public double priceToEarningsRatio(String symbol, int price);
	public void recordTrade(String symbol, LocalDateTime timestamp,  int quantity, boolean isBuy, int price);
	public double volumeWeightedStockPrice(String symbol, LocalDateTime since);
	public double allShareIndex(LocalDateTime since);
	
}
