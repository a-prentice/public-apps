package com.gbce.trading.stock_market_app.stock;

public class PreferredStock extends AbstractStock 
{
	private int fixedDividend;
	
	// constructor
	public PreferredStock(String symbol, int lastDividend, int fixedDividend, int parValue)	{
		super(symbol, lastDividend, parValue);
		this.fixedDividend = fixedDividend;
	}

	@Override
	public double dividendYield(int price) {
		return (((double)fixedDividend/100) * parValue) / price;
	}

}
