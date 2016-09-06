package com.gbce.trading.stock_market_app.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.gbce.trading.stock_market_app.stock.*;

@RunWith(Parameterized.class)
public class CommonStockTest {
	
	// floating point comparison tolerance 
	private static final double TOLERANCE = 1e-6;
	
	 @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {
	        	// lastDividend, par, 		price
                { 0, 			100, 		9999 }, // 
                { 11, 			100, 		9999 }, 
                { 11, 			  0, 		100  }, 
                { 3, 			100, 		0    }, 
	           });
	    }
	    
	private int lastDividend;    
	private int parValue;    
	private int price;    
	    
    public CommonStockTest(int lastDividend, int parValue, int price) 
    {
    	this.lastDividend = lastDividend;    
    	this.parValue = parValue;    
    	this.price = price;    
    }
	    	
	@Test
	public void commonStockDividendYield() 
	{	
		CommonStock stock = new CommonStock("TST", lastDividend, parValue);
		if (price > 0)
			assertEquals((double)lastDividend / price, stock.dividendYield(price), TOLERANCE);
		else {
			//assertEquals(0.0, stock.dividendYield(price), TOLERANCE);		
		}		
	}
		
	@Test
	public void commonStockPriceToEarnings() {
		
		CommonStock stock = new CommonStock("TST", lastDividend, parValue);
		if (price > 0)
			assertEquals(lastDividend > 0 ? (double)price / lastDividend: 0.0, stock.priceToEarningsRatio(price), TOLERANCE);
		else {
			//assertEquals(0.0, stock.dividendYield(price), TOLERANCE);
		}
	}
		
}
