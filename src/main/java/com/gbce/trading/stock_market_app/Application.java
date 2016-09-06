package com.gbce.trading.stock_market_app;

import java.time.Clock;
import java.time.LocalDateTime;


import com.gbce.trading.stock_market_app.stock.InMemoryStockRepository;

public class Application {
	
	public static void main(String [ ] args)
	{
		// example usage
		SuperSimpleStockMarket app = new SuperSimpleStockMarket();
		InMemoryStockRepository repo = new InMemoryStockRepository();
		app.setStockRepository(repo);
		LocalDateTime timestamp = LocalDateTime.now(Clock.systemUTC());
		String symbol = "ALE";
		double value;
		
		app.recordTrade(symbol, timestamp, 2500, true, 100);
		
		value = app.dividendYield(symbol, 105);
		System.out.format("Dividend yield: %f.%n", value);
		
		value = app.priceToEarningsRatio(symbol, 105);
		System.out.format("P/E ratio: %f.%n", value);
		
		value = app.volumeWeightedStockPrice(symbol, timestamp.minusMinutes(5));
		System.out.format("VWSP: %f.%n", value);
		
		value = app.allShareIndex(timestamp.minusMinutes(5));
		System.out.format("All Share Index: %f.%n", value);
		
	}
	
}
