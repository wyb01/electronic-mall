/**
 * 
 */
package com.longIt.shoppingApp.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.longIt.shoppingApp.bean.User;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 * 
 * 定义拦截器，用于判断用户是否登录，如果用户已经登录则放行，否则用户未登录则跳转至登录页面
 */
public class LoginFilter extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 
		//从session中获取用户信息
		User user = (User)request.getSession().getAttribute("session_user");
		
		if(user == null) {
			request.setAttribute("error_message", "您尚未登录，请登录后再进行相关操作！");
			//跳转至登录页面
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		    return false;
		}else {
			return true;
		}
	}

	   
}
