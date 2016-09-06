package com.gbce.trading.stock_market_app.test;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;

import com.gbce.trading.stock_market_app.stock.*;

public class StockRepositoryTest {
		    	
	@Test
	public void testAllStocks() {
		
		StockRepository repo = new InMemoryStockRepository();
		
		List<Stock> stocks = repo.findAllStocks();
		
		assertEquals("TEA symbol is correct", "TEA", stocks.get(0).getSymbol());
		assertEquals("TEA last dividend = 0", 0, stocks.get(0).getLastDividend());
		assertEquals("TEA par value = 100", 100, stocks.get(0).getParValue());
		
		assertEquals("POP symbol is correct", "POP", stocks.get(1).getSymbol());
		assertEquals("POP last dividend = 8", 8, stocks.get(1).getLastDividend());
		assertEquals("POP par value = 100", 100, stocks.get(1).getParValue());
		
		assertEquals("ALE symbol is correct", "ALE", stocks.get(2).getSymbol());
		assertEquals("ALE last dividend = 23", 23, stocks.get(2).getLastDividend());
		assertEquals("ALE par value = 60", 60, stocks.get(2).getParValue());
		
		assertEquals("JOE symbol is correct", "JOE", stocks.get(3).getSymbol());
		assertEquals("JOE last dividend = 13", 13, stocks.get(3).getLastDividend());
		assertEquals("JOE par value = 250", 250, stocks.get(3).getParValue() );

		assertEquals("GIN symbol is correct", "GIN", stocks.get(4).getSymbol());
		assertEquals("GIN last dividend = 8", 8, stocks.get(4).getLastDividend());
		assertEquals("GIN par value = 100", 100, stocks.get(4).getParValue());
						
	}

	@Test
	public void testSingleStock() {
		
		StockRepository repo = new InMemoryStockRepository();
		
		assertEquals("TEA symbol is correct", "TEA", repo.findStock("TEA").getSymbol());
		assertEquals("POP symbol is correct", "POP", repo.findStock("POP").getSymbol());
		assertEquals("ALE symbol is correct", "ALE", repo.findStock("ALE").getSymbol());
		assertEquals("JOE symbol is correct", "JOE", repo.findStock("JOE").getSymbol());
		assertEquals("GIN symbol is correct", "GIN", repo.findStock("GIN").getSymbol());
						
	}
		
	
	
}
