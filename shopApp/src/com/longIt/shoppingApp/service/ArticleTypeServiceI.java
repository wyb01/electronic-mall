/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.util.pager.PageModel;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
public interface ArticleTypeServiceI {

	/**
	 * @return
	 * 获取所有的一级商品类型
	 */
	List<ArticleType> findAllFirstArticleType();

	/**
	 * @param pageModel
	 * @return
	 * 根据商品类型以及商品的标题获取商品信息
	 */
	List<ArticleType> getAllArticleTypes(PageModel pageModel);

	/**
	 * @param code
	 * @return
	 * //加载修改商品类型页面  
	 */
	ArticleType getArticleTypeByCode(String code);

	/**
	 * @param type
	 * 进行更新商品类型操作
	 */
	void updateArticleType(ArticleType type);

	/**
	 * @param type
	 * @param parentCode
	 *  //进行添加商品类型操作
	 */
	void saveArticleType(ArticleType type, String parentCode);

	/**
	 * @param code
	 * @return
	 * 删除商品类型
	 */
	String deleteType(String code);

	

}
