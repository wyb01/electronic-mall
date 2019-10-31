/**
 * 
 */
package com.longIt.shoppingApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.bean.User;
import com.longIt.shoppingApp.service.ArticleServiceI;
import com.longIt.shoppingApp.service.UserServiceI;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceI userService;
	
	  //处理登录请求
	  @RequestMapping("/userLogin")
      public  String userLogin(User user,Model model,HttpSession session) {
    	  
    	   //根据用户输入的账号以及密码获取用户的信息
    	   User u = userService.findUserByNameAndPass(user);
    	   if(u == null) {
    		   model.addAttribute("error_message", "您输入的账号或密码不正确，请核实！");
    		   //跳转至登录页面  /WEB-INF/jsp/login.jsp
    		   return "login";
    	   }else if(u.getDisabled().equals("0")){
    		   model.addAttribute("error_message", "您尚未激活，请打开您的邮箱进行激活操作！");
    		   //跳转至登录页面  /WEB-INF/jsp/login.jsp
    		   return "login";
    	   }else {
    		   //将用户信息存放在session中,直接跳转至首页
    		   session.setAttribute("session_user", u);
    		   return "redirect:/article/index";
    	   }
      }
	  
	  //用户退出
	  @RequestMapping("/logout")
	  public  String logout(HttpSession session) {
		  
		  
		  //将用户信息从session中清除
		  session.removeAttribute("session_user");
		  //用户退出之后重定向至  首页
		  return "redirect:/article/index";
		  
	  }
	  
	  //异步校验账号是否存在
	  @ResponseBody
	  @RequestMapping(value="/validLoginName",produces= {"allpication/text;charset=utf-8"})
	  public  String validLoginName(String loginName) {
		  
		  
		  //校验账号是否存在
		  String result = userService.validLoginName(loginName);
		  return result;
	  }
	  
	  //用户注册
	  @RequestMapping(value="/userRegister")
	  public  String userRegister(Model model,User user) {
		  
		  try {
			  
			  userService.saveUser(user);
			  model.addAttribute("message", "注册成功！");
		  } catch (Exception e) {
			  // TODO: handle exception
			  e.printStackTrace();
			  model.addAttribute("message", "注册失败！");
		  }
		  
		  //返回注册页面
		  return "register";
		  
	  }
	  
	  //用户信息激活
	  @RequestMapping(value="/active")
      public  String active(Model model,String activeCode) {
    	   
		  try {
			  
			  String message = userService.active(activeCode);
			  model.addAttribute("message", !message.equals("") ? message : "激活成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("message", "激活失败！");
		}
		  
		//返回注册页面
		  return "login";
    	 
      }
}
