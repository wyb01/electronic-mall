/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.util.pager.PageModel;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
public interface ArticleServiceI {

	/**
	 * @param title 
	 * @param typeCode 
	 * @param pageModel 
	 * @return
	 * 根据商品类型以及商品的标题获取商品信息
	 */
	List<Article> getAllArticles(String typeCode, String title, PageModel pageModel);

	/**
	 * @param id
	 * @return
	 * 根据商品id获取商品信息
	 */
	Article getArticleById(Integer id);

	/**
	 * @param id
	 * 商品信息下架	
	 */
	void removeArticleById(Integer id);

	/**
	 * @param article
	 *  //保存商品信息
	 */
	void saveArticle(Article article);

	/**
	 * @param article
	 * 更新商品信息
	 */
	void updateArticle(Article article);

}
