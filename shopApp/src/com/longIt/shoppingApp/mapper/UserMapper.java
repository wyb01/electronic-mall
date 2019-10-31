package com.longIt.shoppingApp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.longIt.shoppingApp.bean.User;

/**
 * UserMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-08-04 22:03:23
 * @version 1.0
 */
public interface UserMapper {

	/**
	 * @param user
	 * @return
	 */
	@Select("select * from ec_user where login_name = #{loginName} and password = #{password}")
	User getUserByNameAndPass(User user);

	/**
	 * @param user
	 */
	@Insert("insert into ec_user(login_name,password,name,sex,email,phone,address,create_date,active)  values(#{loginName},#{password},#{name},#{sex},#{email},#{phone},#{address},#{createDate},#{active})")
	void saveUser(User user);

	/**
	 * @param activeCode
	 * //用户信息激活  
	 */
	@Update("update ec_user set disabled = '1' where active = #{activeCode}")
	void activeUser(String activeCode);

	/**
	 * @param loginName
	 * @return
	 * 根据账号获取用户信息
	 */
	@Select("select * from ec_user where login_name = #{loginName}")
	User getUserByLoginName(String loginName);



}