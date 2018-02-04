
package com.djs.learn.spring_sample.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;

public class ItemDaoSpringImplC extends SimpleJdbcDaoSupport implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoSpringImplC.class );

	@Override
	public void save( Item item )
	{
		// SimpleJdbcTemplate works for both JdbcTemplate and NamedParameterJdbcTemplate.

		final String sql = "INSERT INTO TEST_ITEM (SEQ_ID, ITEM_NAME, DESCRIPTION) VALUES (?,?,?)";

		if (log.isInfoEnabled())
		{
			log.info( "Insert: " + item );
		}

		getSimpleJdbcTemplate().update( sql, item.getSeqId(), item.getItemName(), item.getDescription() );
	}

	@Override
	public List<Item> query( String itemName )
	{
		final String sql = "SELECT * FROM TEST_ITEM WHERE ITEM_NAME = ?";

		if (log.isInfoEnabled())
		{
			log.info( "Query: " + itemName );
		}

		List<Item> items = getSimpleJdbcTemplate().query( sql, new ParameterizedRowMapper<Item>()
		{
			public Item mapRow( ResultSet rs, int rowNum ) throws SQLException
			{
				Item item = new Item();
				item.setSeqId( rs.getInt( "SEQ_ID" ) );
				item.setItemName( rs.getString( "ITEM_NAME" ) );
				item.setDescription( rs.getString( "DESCRIPTION" ) );
				item.setAddTime( rs.getTimestamp( "ADD_TIME" ) );

				return item;
			}
		}, itemName );

		if (log.isInfoEnabled())
		{
			log.info( "Result: " + items );
		}

		return items.size() > 0 ? items : null;
	}
}
