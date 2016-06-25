
package com.djs.ejb.stock_sample;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StockInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String tickerSymbol;
	private String name;

	/**
	 * Constructor.
	 */
	public StockInfo()
	{
	}

	/**
	 * Constructor.
	 * 
	 * @param tickerSymbol
	 * @param name
	 */
	public StockInfo( String tickerSymbol, String name )
	{
		this.tickerSymbol = tickerSymbol;
		this.name = name;
	}

	/**
	 * @return String - Ticker symbol.
	 */
	@Id
	public String getTickerSymbol()
	{
		return tickerSymbol;
	}

	/**
	 * @param tickerSymbol
	 *        String.
	 */
	public void setTickerSymbol( String tickerSymbol )
	{
		this.tickerSymbol = tickerSymbol;
	}

	/**
	 * @return String - Name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *        String.
	 */
	public void setName( String name )
	{
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return getTickerSymbol() + ";" + getName();
	}
}