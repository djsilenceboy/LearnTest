
package com.djs.learn.mvc.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.djs.learn.mvc.service.CartService;

@Repository
public class InMemoryOrderRepository implements OrderRepository
{
	private static final Logger logger = Logger.getLogger(InMemoryOrderRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTempleate;

	@Autowired
	private CartService CartService;

	@Override
	public long saveOrder(Order order){
		logger.info("[saveOrder]");

		Long customerId = saveCustomer(order.getCustomer());
		Long shippingDetailId = saveShippingDetail(order.getShippingDetail());

		order.getCustomer().setCustomerId(customerId);
		order.getShippingDetail().setId(shippingDetailId);

		long createdOrderId = createOrder(order);
		CartService.clearCart(order.getCart().getId());

		return createdOrderId;
	}

	private long saveShippingDetail(ShippingDetail shippingDetail){
		logger.info("[saveShippingDetail]");

		long addressId = saveAddress(shippingDetail.getShippingAddress());

		String SQL = "INSERT INTO SHIPPING_DETAIL(NAME,SHIPPING_DATE,SHIPPING_ADDRESS_ID) " + "VALUES (:name, :shippingDate, :addressId)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", shippingDetail.getName());
		params.put("shippingDate", shippingDetail.getShippingDate());
		params.put("addressId", addressId);

		SqlParameterSource paramSource = new MapSqlParameterSource(params);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[]{"ID"});

		return keyHolder.getKey().longValue();
	}

	private long saveCustomer(Customer customer){
		logger.info("[saveCustomer]");

		long addressId = saveAddress(customer.getBillingAddress());

		String SQL = "INSERT INTO CUSTOMER(NAME,PHONE_NUMBER,BILLING_ADDRESS_ID) " + "VALUES (:name, :phoneNumber, :addressId)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", customer.getName());
		params.put("phoneNumber", customer.getPhoneNumber());
		params.put("addressId", addressId);

		SqlParameterSource paramSource = new MapSqlParameterSource(params);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[]{"ID"});

		return keyHolder.getKey().longValue();
	}

	private long saveAddress(Address address){
		logger.info("[saveAddress]");

		String SQL =
		           "INSERT INTO ADDRESS(DOOR_NO,STREET_NAME,AREA_NAME,STATE,COUNTRY,ZIP) " + "VALUES (:doorNo, :streetName, :areaName, :state, :country, :zip)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("doorNo", address.getDoorNo());
		params.put("streetName", address.getStreetName());
		params.put("areaName", address.getAreaName());
		params.put("state", address.getState());
		params.put("country", address.getCountry());
		params.put("zip", address.getZipCode());

		SqlParameterSource paramSource = new MapSqlParameterSource(params);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[]{"ID"});

		return keyHolder.getKey().longValue();
	}

	private long createOrder(Order order){
		logger.info("[createOrder]");

		String SQL = "INSERT INTO ORDERS(CART_ID,CUSTOMER_ID,SHIPPING_DETAIL_ID) " + "VALUES (:cartId, :customerId, :shippingDetailId)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", order.getOrderId());
		params.put("cartId", order.getCart().getId());
		params.put("customerId", order.getCustomer().getCustomerId());
		params.put("shippingDetailId", order.getShippingDetail().getId());

		SqlParameterSource paramSource = new MapSqlParameterSource(params);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[]{"ID"});

		return keyHolder.getKey().longValue();
	}
}
