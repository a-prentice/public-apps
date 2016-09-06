package com.gbce.trading.stock_market_app.stock;

public class CommonStock extends AbstractStock 
{	
	// constructor
	public CommonStock(String symbol, int lastDividend, int parValue)
	{
		super(symbol, lastDividend, parValue);
	}
	
	@Override
	public double dividendYield(int price) {
		
		if (lastDividend == 0)
			return 0;
		
		if (price <= 0)
			throw new IllegalArgumentException();
		
		return (double)lastDividend / price;
	}
}
