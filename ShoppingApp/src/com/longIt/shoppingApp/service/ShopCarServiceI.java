/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.bean.Shopcar;


/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
public interface ShopCarServiceI {

	/**
	 * @param session
	 * @param id
	 * @param number
	 * 将商品信息加入至购物车
	 */
	void addArticleToShopCar(HttpSession session, int id, int number);

	/**
	 * @param session
	 * @return
	 * 展示购物车中的商品信息
	 */
	List<Shopcar> getAllShopCarByUserId(HttpSession session);

	/**
	 * @param session
	 * @param id
	 * @param number
	 * 更新购物车中商品的购物数量
	 */
	void updateShopcar(HttpSession session, int id, int number);

	/**
	 * @param session
	 * @param id
	 * //删除购物车中商品的购物数量
	 */
	void deleteShopcar(HttpSession session, int id);

	
}
