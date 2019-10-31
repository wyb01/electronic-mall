package com.longIt.shoppingApp.mapper;

import java.util.List;

import com.longIt.shoppingApp.bean.Order;

/**
 * OrderMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-05-05 21:31:40
 * @version 1.0
 */
public interface OrderMapper {

	/**
	 * @param order
	 * 保存订单信息
	 */
	void saveOrder(Order order);

	/**
	 * @param userId
	 * @return
	 * 根据用户id获取该用户所有的订单信息
	 */
	List<Order> getOrdersByUserId(int userId);



}