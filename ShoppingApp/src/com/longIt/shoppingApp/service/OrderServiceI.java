/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.bean.Order;


/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
public interface OrderServiceI {

	/**
	 * @param orderInfo
	 * 提交订单
	 */
	void orderSubmit(String orderInfo);

	/**
	 * @return
	 * 根据当前用户的id查询，该用户所有的订单信息
	 */
	List<Order> getOrdersByUserId();


	

}
