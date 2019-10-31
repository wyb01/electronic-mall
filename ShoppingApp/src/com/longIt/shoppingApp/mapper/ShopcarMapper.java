package com.longIt.shoppingApp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.longIt.shoppingApp.bean.Shopcar;

/**
 * ShopcarMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-05-05 21:31:40
 * @version 1.0
 */
public interface ShopcarMapper {

	/**
	 * @param userId
	 * @param id
	 * @return
	 * 
	 */
	@Select("select * from ec_shopcar where user_id = #{userId} and article_id=#{id}")
	Shopcar getShopCarByUserIdAndArticleId(@Param("userId")int userId, @Param("id")int id);

	/**
	 * @param userId
	 * @param id
	 * @param number
	 */
	@Update("update ec_shopcar set buynum = #{number} + buynum where user_id = #{userId} and article_id=#{id}")
	void updateShopCar(@Param("userId")int userId, @Param("id")int id, @Param("number")int number);

	/**
	 * @param userId
	 * @param id
	 * @param number
	 */
	@Insert("insert into ec_shopcar(buynum,user_id,article_id) values(#{number},#{userId},#{id})")
	void addShopCar(@Param("userId")int userId, @Param("id")int id, @Param("number")int number);

	/**
	 * @param userId
	 * @return
	 * 获取用户的购物详情信息
	 */
	List<Shopcar> getAllShopCarByUserId(int userId);

	/**
	 * @param id
	 * @param number
	 * @param number2 
	 * 
	 */
	@Update("update ec_shopcar set buynum = #{buyNum}  where user_id = #{userId} and article_id=#{id}")
	void updateShopcar(@Param("userId")int userId, @Param("id")int id, @Param("buyNum")int buyNum);

	/**
	 * @param userId
	 * @param id
	 */
	@Delete("delete from ec_shopcar where user_id = #{userId} and article_id=#{id}")
	void deleteShopcar(@Param("userId")int userId, @Param("id")int id);



}