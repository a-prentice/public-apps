package com.gbce.trading.stock_market_app.stock;

import java.time.LocalDateTime;
import java.util.List;
import com.gbce.stock_market_app.trading.trade.Trade;

public interface StockRepository 
{
	public void save(Stock stock);
	public Stock findStock(String symbol);
	public List<Stock> findAllStocks();
	public List<Trade> findStockTradesSince(String symbol, LocalDateTime since); 	
}
