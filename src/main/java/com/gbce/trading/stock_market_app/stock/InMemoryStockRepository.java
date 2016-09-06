package com.gbce.trading.stock_market_app.stock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.gbce.stock_market_app.trading.trade.Trade;

public class InMemoryStockRepository implements StockRepository {

	private Map<String, Stock> stocks = new LinkedHashMap<String, Stock>();
	
	public InMemoryStockRepository()
	{
		// standing sample stock data...
		stocks.put("TEA", new CommonStock("TEA", 0, 100));
		stocks.put("POP", new CommonStock("POP", 8, 100));
		stocks.put("ALE", new CommonStock("ALE", 23, 60));
		stocks.put("JOE", new CommonStock("JOE", 13, 250));		
		stocks.put("GIN", new PreferredStock("GIN", 8, 2, 100));
	}
	
	@Override
	public Stock findStock(String symbol)
	{
		return stocks.get(symbol);
	}

	public List<Trade> findStockTradesSince(String symbol, LocalDateTime since) 
	{
		List<Trade> filtered = new ArrayList<Trade>();
		Stock stock =  findStock(symbol);
		if (stock != null)
		{
			Predicate<Trade> timeRange = t -> t.getTimestamp().isAfter(since);	
			filtered = stock.getTrades().stream().filter(timeRange).collect(Collectors.toList());
		}	
		return filtered;
	}	
		
	@Override
	public List<Stock> findAllStocks() 
	{
		return new ArrayList<Stock>(stocks.values());
	}

	
	@Override
	public void save(Stock stock) 
	{
		// nothing to do here for in-memory repository
	}
	
}
