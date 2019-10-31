/**
 * 
 */
package com.longIt.shoppingApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.bean.Order;
import com.longIt.shoppingApp.bean.User;
import com.longIt.shoppingApp.service.ArticleServiceI;
import com.longIt.shoppingApp.service.OrderServiceI;
import com.longIt.shoppingApp.service.UserServiceI;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderServiceI orderServiceI;
	
	
	  
	  //提交订单
	  @RequestMapping(value="/orderSubmit")
      public  String orderSubmit(String orderInfo) {
    	   
		  try {
			  System.out.println("orderInfo:"+orderInfo);
			  orderServiceI.orderSubmit(orderInfo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  
		  //重定向至订单列表
		  return "redirect:/order/showOrder.action";
    	 
      }
	  
	  //查询当前用户所有的订单信息
	  @RequestMapping(value="/showOrder")
      public  String showOrder(Model model) {
    	   
		  try {
			  
			  //根据当前用户的id查询，该用户所有的订单信息
			  List<Order> orders = orderServiceI.getOrdersByUserId();
			  model.addAttribute("orders", orders);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  
		  //跳转至订单列表页面
		  return "order";
    	 
      }
}
