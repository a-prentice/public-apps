package com.gbce.trading.stock_market_app.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.gbce.trading.stock_market_app.stock.PreferredStock;

@RunWith(Parameterized.class)
public class PreferredStockTest {
	
	// floating point comparison tolerance 
	private static final double TOLERANCE = 1e-6;
	
	 @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {
	        	// last dividend, fixed dividend 	par, 		price
                { 1, 				2,				100, 		100 }, // 
                { 10, 				2,				100, 		100 }, // 
                { 10, 				2,				101, 		100 }, // 
                { 10, 				2,				101, 		99  }, // 
	        });
	    }
	    
	private int lastDividend;    
	private int fixedDividend;    
	private int parValue;    
	private int price;    
	    
    public PreferredStockTest(int lastDividend, int fixedDividend, int parValue, int price) 
    {
    	this.lastDividend = lastDividend;    
    	this.fixedDividend = fixedDividend;    
    	this.parValue = parValue;    
    	this.price = price;    
    }
	    	
	@Test
	public void preferredStockDividendYield() 
	{	
		PreferredStock stock = new PreferredStock("TST", lastDividend, fixedDividend, parValue);
		assertEquals(((double)fixedDividend/100 * parValue) / price, stock.dividendYield(price), TOLERANCE);
	}
	
	@Test
	public void preferredStockPriceToEarnings() 
	{	
		PreferredStock stock = new PreferredStock("TST", lastDividend, fixedDividend, parValue);
		assertEquals((double)price / lastDividend, stock.priceToEarningsRatio(price), TOLERANCE);
	}
		
}
