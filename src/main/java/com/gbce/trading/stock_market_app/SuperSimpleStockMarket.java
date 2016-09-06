package com.gbce.trading.stock_market_app;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import com.gbce.stock_market_app.trading.trade.Trade;
import com.gbce.trading.stock_market_app.stock.Stock;
import com.gbce.trading.stock_market_app.stock.StockRepository;

public class SuperSimpleStockMarket implements StockMarket {
	
	private StockRepository stockRepository = null;
	
	public void setStockRepository(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public double dividendYield(String symbol, int price)
	{
		if (price <= 0)
			throw new IllegalArgumentException();
		
		Stock stock = stockRepository.findStock(symbol);
		if (stock == null)
			throw new IllegalArgumentException();
				
		return stock.dividendYield(price);
	}
	
	@Override
	public double priceToEarningsRatio(String symbol, int price)
	{
		if (price <= 0)
			throw new IllegalArgumentException();

		Stock stock = stockRepository.findStock(symbol);
		if (stock == null)
			throw new IllegalArgumentException();
				
		return stock.priceToEarningsRatio(price);
	}
	
	@Override
	public void recordTrade(String symbol, LocalDateTime timestamp,  int quantity, boolean isBuy, int price)
	{		
		if (timestamp.isAfter(LocalDateTime.now(Clock.systemUTC())))
			throw new IllegalArgumentException();

		if (quantity <= 0)
			throw new IllegalArgumentException();

		if (price <= 0)
			throw new IllegalArgumentException();
		
		Stock stock = stockRepository.findStock(symbol);
		if (stock == null)
			throw new IllegalArgumentException();
				
		stock.addTrade(new Trade(timestamp, quantity, isBuy, price));
	}
	
	@Override
	public double volumeWeightedStockPrice(String symbol, LocalDateTime since)
	{
		List<Trade> trades = stockRepository.findStockTradesSince(symbol, since);
		if (trades != null) 
		{
			double numerator = 0;
			double denominator = 0;		
			for(Trade trade: trades)
			{
				int quantity = trade.getQuantity();
				numerator += trade.getPrice() * quantity;
				denominator += quantity;
			}
			return numerator / denominator;	
		}
		return 0;
	}

	@Override
	public double allShareIndex(LocalDateTime since) {
		
		double mean = 0;
		int count = 0;
		double product = 1;
		List<Stock> allStocks = stockRepository.findAllStocks();
		for (Stock stock: allStocks)
		{
			if (stock.getTrades().size() > 0)
			{	
				product *= volumeWeightedStockPrice(stock.getSymbol(), since);				
				count++;
			}	
		}
		if (count != 0)
		{	
			mean = Math.pow(product, 1.0 / count);
		}	
		return mean;
	}
	

}
