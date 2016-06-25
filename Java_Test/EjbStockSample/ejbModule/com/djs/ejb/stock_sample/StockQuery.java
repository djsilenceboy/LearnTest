
package com.djs.ejb.stock_sample;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class StockQuery
 */
@Stateless
public class StockQuery implements StockQueryRemote
{
	@PersistenceContext
	private EntityManager _manager;

	/**
	 * Default constructor.
	 */
	public StockQuery()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see com.djs.ejb.stock_sample.StockSampleRemote#getStockInfo(java.lang.String)
	 */
	public String getStockInfo( String ticker ) throws Exception
	{
		StockInfo stock = _manager.find( StockInfo.class, ticker );

		if (stock == null)
		{
			throw new Exception( "Cannot find stock with ticker " + ticker + "." );
		}

		return stock.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see com.djs.ejb.stock_sample.StockSampleRemote#addStockInfo(java.lang.String, java.lang.String)
	 */
	public void addStockInfo( String ticker, String name ) throws Exception
	{
		StockInfo stock = _manager.find( StockInfo.class, ticker );

		if (stock != null)
		{
			throw new Exception( "Already exist stock with ticker " + ticker + "." );
		}

		_manager.persist( new StockInfo( ticker, name ) );
	}

	/*
	 * (non-Javadoc)
	 * @see com.djs.ejb.stock_sample.StockSampleRemote#updateStockInfo(java.lang.String, java.lang.String)
	 */
	public void updateStockInfo( String ticker, String name ) throws Exception
	{
		StockInfo stock = _manager.find( StockInfo.class, ticker );

		if (stock == null)
		{
			throw new Exception( "Cannot find stock with ticker " + ticker + "." );
		}

		stock.setName( name );
	}

	/*
	 * (non-Javadoc)
	 * @see com.djs.ejb.stock_sample.StockSampleRemote#deleteStockInfo(java.lang.String)
	 */
	public void deleteStockInfo( String ticker ) throws Exception
	{
		StockInfo stock = _manager.find( StockInfo.class, ticker );

		if (stock == null)
		{
			throw new Exception( "Cannot find stock with ticker " + ticker + "." );
		}

		_manager.remove( stock );
	}
}
