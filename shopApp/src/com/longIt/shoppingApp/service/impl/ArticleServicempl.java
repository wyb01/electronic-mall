/**
 * 
 */
package com.longIt.shoppingApp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.mapper.ArticleMapper;
import com.longIt.shoppingApp.service.ArticleServiceI;
import com.longIt.util.pager.PageModel;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
@Service(value="articleService")
@Transactional
public class ArticleServicempl implements ArticleServiceI {

    @Autowired
	private ArticleMapper articleMapper;

	/* (non-Javadoc)
	 * 根据商品类型以及商品的标题获取商品信息
	 */
	@Override
	public List<Article> getAllArticles(String typeCode,String title,PageModel pageModel) {
		// TODO Auto-generated method stub
		
		//进行分页查询之前获取总记录数
		int totalNum = articleMapper.getTotalNum(typeCode,title);
		pageModel.setTotalNum(totalNum);
		
		return articleMapper.getAllArticles(typeCode,title,pageModel);
	}

	/* (non-Javadoc)
	 * 根据商品id获取商品信息
	 */
	@Override
	public Article getArticleById(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.getArticleById(id);
	}

	/* (non-Javadoc)
	 * @see com.longIt.shoppingApp.service.ArticleServiceI#removeArticleById(java.lang.Integer)
	 * 商品信息下架	
	 */
	@Override
	public void removeArticleById(Integer id) {
		// TODO Auto-generated method stub
		articleMapper.removeArticleById(id);
	}

	/* (non-Javadoc)
	 *  //保存商品信息
	 */
	@Override
	public void saveArticle(Article article) {
		// TODO Auto-generated method stub
		article.setCreateDate(new Date());
        article.setPutawayDate(new Date());
		articleMapper.saveArtice(article);
	}

	/* (non-Javadoc)
	 * @see com.longIt.shoppingApp.service.ArticleServiceI#updateArticle(com.longIt.shoppingApp.bean.Article)
	 * 更新商品信息
	 */
	@Override
	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		articleMapper.updateArticle(article);
	}

}
