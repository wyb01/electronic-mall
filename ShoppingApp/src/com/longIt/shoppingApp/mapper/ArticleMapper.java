package com.longIt.shoppingApp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.util.pager.PageModel;

/**
 * ArticleMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-05-05 21:31:40
 * @version 1.0
 */
public interface ArticleMapper {

	/**
	 * @param typeCode
	 * @return
	 * 根据商品类型获取商品信息
	 */
	List<Article> findAllArticle(@Param("typeCode")String typeCode,@Param("keyword")String keyword,@Param("pageModel")PageModel pageModel);

	/**
	 * @param id
	 * @return
	 * 根据商品id获取商品详情信息
	 */
	@Select("select * from ec_article where id = #{id}")
	Article getArticleById(Integer id);

	/**
	 * @param typeCode
	 * @param keyword
	 * @return
	 * 查询总记录数
	 */
	int findTotalNum(@Param("typeCode")String typeCode, @Param("keyword")String keyword);

	



}