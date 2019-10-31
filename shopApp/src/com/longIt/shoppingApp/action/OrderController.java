/**
 * 
 */
package com.longIt.shoppingApp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.longIt.shoppingApp.service.ArticleTypeServiceI;
import com.longIt.shoppingApp.service.OrderServiceI;
import com.longIt.shoppingApp.service.UserServiceI;
import com.longIt.util.Constant;
import com.longIt.util.pager.PageModel;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderServiceI orderService;
	
	
	 //订单分页查询
	 @RequestMapping("/getAll.action")
	 public String getAll(PageModel pageModel,Model model) {
		 
		 List<Order> orders = orderService.getAll(pageModel);
		 model.addAttribute("orders", orders);
		 return "order/list";
		 
	 }
	 
	 
	 
	 //确认发送订单   
	 @RequestMapping("/checkOrder.action")
	 public String checkOrder(Order order) {
		 orderService.checkOrder(order);
		 
		 return "redirect:/order/getAll.action";
		 
	 }
	
}
