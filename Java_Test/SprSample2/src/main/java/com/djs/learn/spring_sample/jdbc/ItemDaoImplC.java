
package com.djs.learn.spring_sample.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;

public class ItemDaoImplC implements ItemDao
{
	private final Logger log = Logger.getLogger(ItemDaoImplC.class);

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		if (log.isTraceEnabled()) {
			log.trace("Set " + jdbcTemplate.getClass().getName());
		}

		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save(Item item){
		// JdbcTemplate works for both JdbcTemplate and NamedParameterJdbcTemplate.

		final String sql = "INSERT INTO TEST_ITEM (SEQ_ID, ITEM_NAME, DESCRIPTION) VALUES (?,?,?)";

		if (log.isInfoEnabled()) {
			log.info("Insert: " + item);
		}

		jdbcTemplate.update(sql, item.getSeqId(), item.getItemName(), item.getDescription());
	}

	@Override
	public List<Item> query(String itemName){
		final String sql = "SELECT * FROM TEST_ITEM WHERE ITEM_NAME = ?";

		if (log.isInfoEnabled()) {
			log.info("Query: " + itemName);
		}

		List<Item> items = jdbcTemplate.query(sql, new Object[]{itemName}, new RowMapper<Item>() {
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException{
				Item item = new Item();
				item.setSeqId(rs.getInt("SEQ_ID"));
				item.setItemName(rs.getString("ITEM_NAME"));
				item.setDescription(rs.getString("DESCRIPTION"));
				item.setAddTime(rs.getTimestamp("ADD_TIME"));

				return item;
			}
		});

		if (log.isInfoEnabled()) {
			log.info("Result: " + items);
		}

		return items.size() > 0 ? items : null;
	}
}
