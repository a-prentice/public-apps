package com.gbce.trading.stock_market_app.stock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gbce.stock_market_app.trading.trade.Trade;

abstract class AbstractStock implements Stock 
{
	protected final String symbol;
	protected final int lastDividend;
	protected final int parValue;
	private List<Trade> trades = new ArrayList<Trade>();
	
	protected AbstractStock(String symbol, int dividend, int parValue)
	{
		this.symbol = symbol;
		this.lastDividend = dividend;
		this.parValue = parValue;		
	}
	
	public final int getLastDividend() 
	{
		return lastDividend;
	}

	public final void addTrade(LocalDateTime timestamp, int quantity, boolean isBuy, int price) 
	{	
		trades.add(new Trade(timestamp, quantity, isBuy, price));
	}

	public final String getSymbol() 
	{
		return symbol;
	}

	public final int getParValue() 
	{
		return parValue;
	}
		
	@Override
	public final double priceToEarningsRatio(int price) 
	{
		if (lastDividend == 0)
			return 0;
		else
			return (double)price / lastDividend;
	}
	
	@Override
	public final void addTrade(Trade trade) 
	{
		trades.add(trade);		
	}

	@Override
	public final List<Trade> getTrades() 
	{	
		// deep copy so that trades can't be modified
		List<Trade> copy = new ArrayList<Trade>();
		for (Trade trade: trades)
		{
			copy.add(new Trade(trade));
		}
		return copy;
	}
	
}
