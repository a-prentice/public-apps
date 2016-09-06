package com.gbce.stock_market_app.trading.trade;

import java.time.LocalDateTime;

public class Trade {
	
	private final LocalDateTime timestamp;
	private final boolean isBuy;
	private final int quantity;	
	private final int price;
	
	// constructor
	public Trade(LocalDateTime timestamp, int quantity, boolean isBuy, int price) 
	{
		this.timestamp = timestamp;
		this.isBuy = isBuy;
		this.quantity = quantity;
		this.price = price;
	}

	// copy constructor
	public Trade(Trade trade) 
	{
		this(trade.getTimestamp(), trade.getQuantity(), trade.isBuy(), trade.getPrice());
	}

	public LocalDateTime getTimestamp() 
	{
		return timestamp;
	}

	public boolean isBuy() 
	{
		return isBuy;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public int getPrice() 
	{
		return price;
	}
	
}
