
package com.djs.test.spring_sample.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.djs.test.spring_sample.db.Item;
import com.djs.test.spring_sample.db.ItemDao;

public class ItemDaoImplA implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoImplA.class );

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate( JdbcTemplate jdbcTemplate )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Set " + jdbcTemplate.getClass().getName() );
		}

		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save( Item item )
	{
		final String sql = "INSERT INTO TEST_ITEM (SEQ_ID, ITEM_NAME, DESCRIPTION) VALUES (?,?,?)";

		if (log.isInfoEnabled())
		{
			log.info( "Insert: " + item );
		}

		jdbcTemplate.update( sql, new Object []
		{ item.getSeqId(), item.getItemName(), item.getDescription() } );
	}

	@Override
	public List<Item> query( String itemName )
	{
		final String sql = "SELECT * FROM TEST_ITEM WHERE ITEM_NAME = ?";

		if (log.isInfoEnabled())
		{
			log.info( "Query: " + itemName );
		}

		List items = jdbcTemplate.query( sql, new Object []
		{ itemName }, new RowMapper()
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
