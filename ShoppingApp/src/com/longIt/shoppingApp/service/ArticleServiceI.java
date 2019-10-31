/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.util.pager.PageModel;


/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
public interface ArticleServiceI {

	/**
	 * @return
	 * 获取所有的一级物品类型
	 */
	List<ArticleType> findAllFirstArticleType();

	/**
	 * @param pageModel 
	 * @param object
	 * @return
	 * 根据商品类型获取商品信息
	 */
	List<Article> findAllArticle(String typeCode,String keyword, PageModel pageModel);

	/**
	 * @param string
	 * @return
	 * //根据一级物品类型获取对应的二级物品类型信息
	 */
	List<ArticleType> findAllSecondArticleTypes(String string);

	/**
	 * @param id
	 * @return
	 * 根据商品id获取商品详情信息
	 */
	Article getArticleById(Integer id);

	/**
	 * @param object
	 * @param object2
	 * @return
	 * 查询总记录数
	 */
	int findTotalNum(String typeCode, String keyword);

	

}
