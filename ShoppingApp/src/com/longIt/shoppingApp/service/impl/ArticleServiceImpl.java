/**
 * 
 */
package com.longIt.shoppingApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.mapper.ArticleMapper;
import com.longIt.shoppingApp.mapper.ArticleTypeMapper;
import com.longIt.shoppingApp.service.ArticleServiceI;
import com.longIt.shoppingApp.util.pager.PageModel;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleServiceI {
	
	@Autowired
	private ArticleTypeMapper articleTypeMapper;
	
	@Autowired
	private ArticleMapper articleMapper;

	/* (non-Javadoc)
	 *获取所有的一级物品类型
	 */
	@Override
	public List<ArticleType> findAllFirstArticleType() {
		// TODO Auto-generated method stub
		List<ArticleType> articleTypes = articleTypeMapper.findAllFirstArticleType();
		return articleTypes;
	}

	/* (non-Javadoc)
	 * 根据商品类型获取商品信息
	 */
	@Override
	public List<Article> findAllArticle(String typeCode,String keyword,PageModel pageModel) {
		// TODO Auto-generated method stub
		List<Article> articles = articleMapper.findAllArticle(typeCode,keyword,pageModel);
		return articles;
	}

	/* (non-Javadoc)
	 * 根据一级物品类型获取对应的二级物品类型信息
	 */
	@Override
	public List<ArticleType> findAllSecondArticleTypes(String typeCode) {
		// TODO Auto-generated method stub
		 List<ArticleType>  articleTypes = articleTypeMapper.findAllSecondArticleTypes(typeCode);
		return articleTypes;
	}

	/* (non-Javadoc)
	 * 根据商品id获取商品详情信息
	 */
	@Override
	public Article getArticleById(Integer id) {
		// TODO Auto-generated method stub
		//根据商品id获取商品详情信息
		Article article = articleMapper.getArticleById(id);
		return article;
	}

	/* (non-Javadoc)
	 * 查询总记录数
	 */
	@Override
	public int findTotalNum(String typeCode, String keyword) {
		// TODO Auto-generated method stub
		return articleMapper.findTotalNum(typeCode,keyword);
	}
	
	
}
