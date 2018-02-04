
package com.djs.learn.spring_sample.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;

public class ItemDaoSpringImplB extends NamedParameterJdbcDaoSupport implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoSpringImplB.class );

	@Override
	public void save( Item item )
	{
		final String sql = "INSERT INTO TEST_ITEM (SEQ_ID, ITEM_NAME, DESCRIPTION) VALUES (:seqId, :itemName, :descroption)";

		if (log.isInfoEnabled())
		{
			log.info( "Insert: " + item );
		}

		Map parameters = new HashMap();
		parameters.put( "seqId", item.getSeqId() );
		parameters.put( "itemName", item.getItemName() );
		parameters.put( "descroption", item.getDescription() );

		getNamedParameterJdbcTemplate().update( sql, parameters );
	}

	@Override
	public List<Item> query( String itemName )
	{
		final String sql = "SELECT * FROM TEST_ITEM WHERE ITEM_NAME = :itemName";
		Map parameters = new HashMap();
		parameters.put( "itemName", itemName );

		if (log.isInfoEnabled())
		{
			log.info( "Query: " + itemName );
		}

		List items = getNamedParameterJdbcTemplate().query( sql, parameters, new RowMapper()
		{
			public Object mapRow( ResultSet rs, int rowNum ) throws SQLException, DataAccessException
			{
				Item item = new Item();
				item.setSeqId( rs.getInt( "SEQ_ID" ) );
				item.setItemName( rs.getString( "ITEM_NAME" ) );
				item.setDescription( rs.getString( "DESCRIPTION" ) );
				item.setAddTime( rs.getTimestamp( "ADD_TIME" ) );

				return item;
			}
		} );

		if (log.isInfoEnabled())
		{
			log.info( "Result: " + items );
		}

		return items.size() > 0 ? (List<Item>)items : null;
	}
}
