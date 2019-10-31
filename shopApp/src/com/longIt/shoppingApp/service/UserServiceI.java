/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.User;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
public interface UserServiceI {

	/**
	 * @param loginName
	 * @param password
	 * @return
	 * 根据账号以及密码获取用户信息
	 */
	User getUserByNameAndPass(User user);

	/**
	 * @param user
	 *  //用户信息注册   
	 */
	void saveUser(User user,HttpServletRequest request) throws Exception;

	/**
	 * @param activeCode
	 * //用户信息激活  
	 */
	void activeUser(String activeCode);

	/**
	 * @param loginName
	 * @return
	 * 根据账号获取用户信息
	 */
	String getUserByLoginName(String loginName);

	

}
