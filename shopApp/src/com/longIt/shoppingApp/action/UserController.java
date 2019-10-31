/**
 * 
 */
package com.longIt.shoppingApp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.longIt.shoppingApp.service.ArticleTypeServiceI;
import com.longIt.shoppingApp.service.UserServiceI;
import com.longIt.util.Constant;

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
	
	
	 //跳转至登录页面	
	 @RequestMapping("/showLoginPage")
	 public String showLoginPage() {
		 
		
		 return "user/login";
		 
	 }
	 
	 
	 //处理登录请求
	 @RequestMapping("/userLogin")
	 public String userLogin(User user,Model model,HttpSession session) {
		 
		 User u = userService.getUserByNameAndPass(user);
		 if(u == null) {
			 //账号或密码不正确，给用户提示信息，并跳转至登录页面
			 model.addAttribute("tip", "您输入的账号或密码不正确请核实!");
			 
			 return "user/login";
		 }else if("0".equals(u.getDisabled())){
			 model.addAttribute("tip", "您尚未激活，请激活后再进行相关操作!");
			 return "user/login";
		 }else {
			 //跳转至首页，并将用户的信息保存至session中
			 session.setAttribute(Constant.SESSION_USER, u);
			 return "redirect:/article/getAll.action";
			 
		 }
		
 
	 }
	 
	 //用户退出   
	 @RequestMapping("/logout")
	 public String logout(HttpSession session) {
		 
		 //将用户的信息从session中清除
		 session.removeAttribute(Constant.SESSION_USER);
		
		 return "user/login";
		 
	 }
	 
	 //展示用户注册页面  showRegisterPage
	 @RequestMapping("/showRegisterPage")
	 public String showRegisterPage() {
		 
		
		 return "user/register";
		 
	 }
	 
	 //用户信息注册   
	 @RequestMapping("/userRegister")
	 public String userRegister(User user,Model model,HttpServletRequest request) {
		 
		 try {
			 userService.saveUser(user,request);
			 model.addAttribute("tip", "注册成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 model.addAttribute("tip", "注册失败:"+e.getMessage());
		}
		
		 return "user/login";
		 
	 }
	 
	 //用户信息激活   
	 @RequestMapping("/active")
	 public String active(String activeCode,Model model) {
		 
		 try {
			 userService.activeUser(activeCode);
			 model.addAttribute("tip", "激活成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 model.addAttribute("tip", "激活失败:"+e.getMessage());
		}
		
		 return "user/login";
		 
	 }
	 
	 //异步校验账号是否存在
	 @ResponseBody
	 @RequestMapping("/validName")
	 public String validName(String loginName) {
		 
		 String  tip = userService.getUserByLoginName(loginName);
		
		 return tip;
		 
	 }
}
