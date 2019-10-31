package com.longIt.shoppingApp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.longIt.shoppingApp.bean.ArticleType;

/**
 * ArticleTypeMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-05-05 21:31:40
 * @version 1.0
 */
public interface ArticleTypeMapper {

	/**
	 * @return
	 * 获取所有的一级物品类型
	 */
	@Select("select * from ec_article_type where length(code) = 4")
	List<ArticleType> findAllFirstArticleType();

	/**
	 * @param typeCode
	 * @return
	 * 根据一级物品类型获取对应的二级物品类型信息
	 */
	@Select("select * from ec_article_type where code like #{typeCode} and length(code) =8")
	List<ArticleType> findAllSecondArticleTypes(String typeCode);



}