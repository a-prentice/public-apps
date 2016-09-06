package com.gbce.trading.stock_market_app.test;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.gbce.stock_market_app.trading.trade.Trade;
import com.gbce.trading.stock_market_app.SuperSimpleStockMarket;
import com.gbce.trading.stock_market_app.stock.InMemoryStockRepository;
import com.gbce.trading.stock_market_app.stock.Stock;

public class EndToEndTest {

	// floating point comparison tolerance 
	private static final double TOLERANCE = 1e-6;
	SuperSimpleStockMarket app;
	InMemoryStockRepository repo = new InMemoryStockRepository();
	
	@Before
	public void initiliase()
	{	
		app = new SuperSimpleStockMarket();
		app.setStockRepository(repo);
	}
		
	@Test
	public void testRatios() 
	{	
		assertEquals("POP Dividend yield", 8.0 / 999, app.dividendYield("POP", 999), TOLERANCE);
		assertEquals("POP P/E Ratio", 101.0 / 8, app.priceToEarningsRatio("POP", 101), TOLERANCE);	
		assertEquals("GIN Dividend yield", (0.02 * 100) / 999, app.dividendYield("GIN", 999), TOLERANCE);
		assertEquals("GIN P/E Ratio", 101.0 / 8, app.priceToEarningsRatio("GIN", 101), TOLERANCE);	
	}
	
	@Test
	public void testVolumeWeightedStockPriceSingle() 
	{	
		Stock stock = repo.findStock("POP");
		stock.addTrade(new Trade(LocalDateTime.now(Clock.systemUTC()), 10, true, 100));				
		double actual = app.volumeWeightedStockPrice("POP", LocalDateTime.now(Clock.systemUTC()).minusMinutes(5));
		assertEquals("VWSP Single", 100.0, actual, TOLERANCE);
	}

	@Test
	public void testVolumeWeightedStockPriceMultiple() 
	{	
		Stock stock = repo.findStock("GIN");
		stock.addTrade(new Trade(LocalDateTime.now(Clock.systemUTC()), 10, true, 100));
		stock.addTrade(new Trade(LocalDateTime.now(Clock.systemUTC()), 1000, true, 98));
		stock.addTrade(new Trade(LocalDateTime.now(Clock.systemUTC()), 300, true, 102));
		// this one shouldn't be included in VWSP
		stock.addTrade(new Trade(LocalDateTime.now(Clock.systemUTC()).minusMinutes(6), 300, true, 102));
				
		double actual = app.volumeWeightedStockPrice("GIN", LocalDateTime.now(Clock.systemUTC()).minusMinutes(5));
		assertEquals("VWSP Multiple", ((10.0*100)+(1000*98)+(300*102))/1310.0, actual, TOLERANCE);	
	}

	@Test
	public void testAllShareIndexSingle() 
	{	
		Stock stock = repo.findStock("GIN");
		stock.addTrade(new Trade(LocalDateTime.now(Clock.systemUTC()), 10, true, 100));				
		double actual = app.allShareIndex(LocalDateTime.now(Clock.systemUTC()).minusMinutes(5));
		assertEquals("Single trade all share index", 100.0, actual, TOLERANCE);	
	}

	@Test
	public void testAllShareIndexMuliple() 
	{	
		Stock stock = repo.findStock("GIN");
		stock.addTrade(new Trade(LocalDateTime.now(Clock.systemUTC()), 10, true, 100));
		stock = repo.findStock("POP");
		stock.addTrade(new Trade(LocalDateTime.now(Clock.systemUTC()), 25, true, 150));				
		double actual = app.allShareIndex(LocalDateTime.now(Clock.systemUTC()).minusMinutes(5));
		assertEquals("Multiple trade all share index", Math.sqrt(150 * 100), actual, TOLERANCE);	
	}

}
