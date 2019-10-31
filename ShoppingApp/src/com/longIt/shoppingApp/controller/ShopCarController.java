/**
 * 
 */
package com.longIt.shoppingApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.longIt.shoppingApp.bean.Shopcar;
import com.longIt.shoppingApp.mapper.ShopcarMapper;
import com.longIt.shoppingApp.service.ShopCarServiceI;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
@Controller
@RequestMapping("/shopCar")
public class ShopCarController {
	
	@Autowired
	private ShopCarServiceI shopCarService;
	
	@RequestMapping("/addToCar.action")
	public String addShopCar(HttpSession session,@Param("id")int id,@Param("number")int number) {
		
		shopCarService.addArticleToShopCar(session,id,number);
		
		//商品加入购物车成功之后，立马展示购物车中的商品信息
		return "redirect:/shopCar/showShopCar.action";
	}
	
	//展示购物车中的商品信息
	@RequestMapping("/showShopCar.action")
	public String showShopCar(HttpSession session,Model model) {
		
		//根据用户的id获取该用户购物详情
		List<Shopcar> shopCars = shopCarService.getAllShopCarByUserId(session);
		model.addAttribute("shopCars", shopCars);
		
		//定义总金额
		double totalPrice = 0.0;
		
		//遍历集合  计算购物车中商品的总金额
		for(Shopcar shopcar : shopCars) {
			totalPrice += shopcar.getArticle().getDiscountPrice() * shopcar.getBuynum();
		}
		
		model.addAttribute("totalPrice", totalPrice);
		
		//跳转至展示购物车中商品信息的页面
		return "shopCar";
	}
	
	//更新购物车中商品的信息
	@RequestMapping("/updateShopcar.action")
	public String updateShopcar(HttpSession session,@Param("id")int id,@Param("number")int number) {
		
		//更新购物车中商品的购物数量
		shopCarService.updateShopcar(session,id,number);
		
		//商品加入购物车成功之后，立马展示购物车中的商品信息
		return "redirect:/shopCar/showShopCar.action";
	}
	
	//删除购物车中商品的信息
	@RequestMapping("/deleteShopCar.action")
	public String deleteShopCar(HttpSession session,@Param("id")int id) {
		
		//删除购物车中商品的购物数量
		shopCarService.deleteShopcar(session,id);
		
		//商品加入购物车成功之后，立马展示购物车中的商品信息
		return "redirect:/shopCar/showShopCar.action";
	}

}
