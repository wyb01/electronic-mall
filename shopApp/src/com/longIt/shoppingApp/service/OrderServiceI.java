/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.Order;
import com.longIt.shoppingApp.bean.User;
import com.longIt.util.pager.PageModel;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
public interface OrderServiceI {

	/**
	 * @param pageModel
	 * @return
	 * //订单分页查询
	 */
	List<Order> getAll(PageModel pageModel);

	/**
	 * @param order
	 * //确认发送订单   
	 */
	void checkOrder(Order order);

	

}
