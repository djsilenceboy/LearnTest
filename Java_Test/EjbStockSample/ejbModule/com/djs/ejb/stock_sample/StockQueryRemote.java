
package com.djs.ejb.stock_sample;

import javax.ejb.Remote;

@Remote
public interface StockQueryRemote
{
	/**
	 * Get stock name by ticker.
	 * 
	 * @param ticker
	 *        String.
	 * @return String - Name.
	 * @throws Exception
	 */
	public String getStockInfo( String ticker ) throws Exception;

	/**
	 * Add stock info.
	 * 
	 * @param ticker
	 *        String.
	 * @param name
	 * @throws Exception
	 */
	public void addStockInfo( String ticker, String name ) throws Exception;

	/**
	 * Update stock info.
	 * 
	 * @param ticker
	 *        String.
	 * @param name
	 *        String.
	 * @throws Exception
	 */
	public void updateStockInfo( String ticker, String name ) throws Exception;

	/**
	 * Delete stock info by ticker.
	 * 
	 * @param ticker
	 *        String.
	 * @throws Exception
	 */
	public void deleteStockInfo( String ticker ) throws Exception;
}
