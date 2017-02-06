
package com.djs.learn.mvc.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djs.learn.mvc.domain.Order;
import com.djs.learn.mvc.domain.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService
{
	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Long saveOrder(Order order){
		logger.info("[saveOrder]");

		return orderRepository.saveOrder(order);
	}
}
