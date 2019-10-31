package com.longIt.shoppingApp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.util.pager.PageModel;

/**
 * ArticleTypeMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-08-04 22:03:23
 * @version 1.0
 */
public interface ArticleTypeMapper {

	/**
	 * @return
	 * 获取所有的一级商品类型
	 */
	@Select("select * from ec_article_type where length(code) = 4")
	List<ArticleType> findAllFirstArticleType();

	/**
	 * @return
	 */
	@Select("select count(*) from ec_article_type")
	int findTotalNum();

	/**
	 * @param pageModel
	 * @return
	 * 根据商品类型以及商品的标题获取商品信息
	 */
	@Select("select * from ec_article_type limit #{startNum} , #{pageSize}")
	List<ArticleType> getAllArticleTypes(PageModel pageModel);

	/**
	 * @param code
	 * @return
	 * 加载修改商品类型页面  
	 */
	@Select("select * from ec_article_type where code = #{code}")
	ArticleType getArticleTypeByCode(String code);

	/**
	 * @param type
	 * 进行更新商品类型操作
	 */
	@Update("update ec_article_type set name = #{name},remark=#{remark} where code = #{code}")
	void updateArticleType(ArticleType type);

	/**
	 * @param string
	 * @return
	 */
	@Select("SELECT MAX(CODE) FROM ec_article_type WHERE CODE LIKE #{parentCode} and length(code) = 8")
	String findMaxSeCode(String parentCode);

	/**
	 * @return
	 * 获取最大的一级物品类型code
	 */
	@Select("SELECT MAX(CODE) FROM ec_article_type WHERE  length(code) = 4")
	String findMaxFiCode();

	/**
	 * @param type
	 */
	@Insert("insert into ec_article_type(code,name,remark) values(#{code},#{name},#{remark})")
	void saveArticleType(ArticleType type);

	/**
	 * @param code
	 * @param i
	 * @return
	 */
	@Select("SELECT * FROM ec_article_type WHERE CODE LIKE #{code} and length(code) = #{len}")
	List<ArticleType> getArticleType(@Param("code")String code, @Param("len")int len);

	/**
	 * @param code
	 */
	@Delete("delete from ec_article_type where code = #{code}")
	void deleteTypes(String code);



}