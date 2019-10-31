/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.bean.User;


/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
public interface UserServiceI {

	/**
	 * @param user
	 * @return
	 * //根据用户输入的账号以及密码获取用户的信息
	 */
	User findUserByNameAndPass(User user);

	/**
	 * @param loginName
	 * @return
	 * //异步校验账号是否存在
	 */
	String validLoginName(String loginName);

	/**
	 * @param user
	 *  //用户注册
	 */
	void saveUser(User user);

	/**
	 * @param activeCode
	 * //用户信息激活
	 */
	String active(String activeCode);

	

}
