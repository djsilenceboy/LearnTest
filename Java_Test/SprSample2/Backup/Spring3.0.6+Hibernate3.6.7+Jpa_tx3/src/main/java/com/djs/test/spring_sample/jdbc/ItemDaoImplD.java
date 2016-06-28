
package com.djs.test.spring_sample.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.djs.test.spring_sample.db.Item;
import com.djs.test.spring_sample.db.ItemDao;

public class ItemDaoImplD implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoImplD.class );

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate( JdbcTemplate jdbcTemplate )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Set " + jdbcTemplate.getClass().getName() );
		}

		this.jdbcTemplate = jdbcTemplate;

		jdbcTemplate.setResultsMapCaseInsensitive( true );
	}

	@Override
	public void save( Item item )
	{
		// SimpleJdbcTemplate works for both JdbcTemplate and NamedParameterJdbcTemplate.

		final String sql = "INSERT INTO TEST_ITEM (SEQ_ID, ITEM_NAME, DESCRIPTION) VALUES (?,?,?)";

		if (log.isInfoEnabled())
		{
			log.info( "Insert: " + item );
		}

		jdbcTemplate.update( sql, item.getSeqId(), item.getItemName(), item.getDescription() );
	}

	@Override
	public List<Item> query( String itemName )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Query: " + itemName );
		}

		SimpleJdbcCall simpleJbdcCall =
		                                new SimpleJdbcCall( jdbcTemplate ).withProcedureName( "TEST_GET_ITEMS" )
		                                        .returningResultSet( "itemList", new ParameterizedRowMapper<Item>()
		                                        {
			                                        @Override
			                                        public Item mapRow( ResultSet rs, int rowNum ) throws SQLException
			                                        {
				                                        Item item = new Item();
				                                        item.setSeqId( rs.getInt( "SEQ_ID" ) );
				                                        item.setItemName( rs.getString( "ITEM_NAME" ) );
				                                        item.setDescription( rs.getString( "DESCRIPTION" ) );
				                                        item.setAddTime( rs.getTimestamp( "ADD_TIME" ) );

				                                        return item;
			                                        }
		                                        } );
		SqlParameterSource in = new MapSqlParameterSource().addValue( "itemName", itemName );
		Map out = simpleJbdcCall.execute( in );
		List<Item> items = (List<Item>)out.get( "itemList" );

		if (log.isInfoEnabled())
		{
			log.info( "Result: " + items );
		}

		return items.size() > 0 ? items : null;
	}
}
