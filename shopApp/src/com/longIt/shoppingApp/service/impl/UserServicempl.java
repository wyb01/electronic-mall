/**
 * 
 */
package com.longIt.shoppingApp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
/*import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;*/
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longIt.shoppingApp.bean.User;
import com.longIt.shoppingApp.mapper.UserMapper;
import com.longIt.shoppingApp.service.UserServiceI;
import com.sun.mail.smtp.SMTPMessage;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
@Service(value="userService")
@Transactional
public class UserServicempl implements UserServiceI {
	
	@Autowired
	private UserMapper userMapper;

	/* (non-Javadoc)
	 *  根据账号以及密码获取用户信息
	 */
	@Override
	public User getUserByNameAndPass(User user) {
		// TODO Auto-generated method stub
		return userMapper.getUserByNameAndPass(user);
	}

	/* (non-Javadoc)
	 * //用户信息注册   
	 */
	@Override
	public void saveUser(User user,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		user.setCreateDate(new Date());
		
		//通过UUID生成激活码
		String activeCode =  UUID.randomUUID().toString();
		user.setActive(activeCode);
		userMapper.saveUser(user);
		
		
		/*******************************邮件发送*********************************/
		//1、创建	Properties对象用于封装邮件服务器相关信息
		Properties props = new Properties();
		// 服务器地址
		props.setProperty("mail.smtp.host", "smtp.126.com");
		// 服务器需要鉴权
		props.setProperty("mail.smtp.auth", "true");
		
		
		//2、创建Authenticator的实例，实现账户、密码的鉴权。
        Authenticator auth = new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("luochunlong666@126.com", "luochunlong123");
            }
        };
        
        //3).获得Session实例
        Session mailSession = Session.getInstance(props, auth);
        
        //4).创建SMTPMessage，要提供session
        SMTPMessage msg = new SMTPMessage(mailSession);
        
       // 5).设置Message的内容，包括发送者、接收者、消息体
	     // 设置邮件标题，没有标题的邮件几乎都会被认为是垃圾邮件被系统退回。
	     msg.setSubject("用户注册激活邮件，请勿回复，按照指引激活");
	     // 设置消息的HTML格式的内容
	     msg.setContent("<a href='http://127.0.0.1:8080/"+request.getContextPath()+"/user/active?activeCode="+user.getActive()+"' target='_blank'>恭喜您注册成功，点击该链接进行激活，此邮件无需回复!</a>","text/html;charset=UTF-8");
	     // 设置发送人
		msg.setFrom(new InternetAddress("luochunlong666@126.com"));
	     // 设置接收者
	     // 接收者类型由：TO(收件人)、CC(抄送)、BCC(密送)
		msg.setRecipient(RecipientType.TO,new InternetAddress(user.getEmail()));
    
        // 发送邮件
		Transport.send(msg);
		
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.longIt.shoppingApp.service.UserServiceI#activeUser(java.lang.String)
	 * //用户信息激活  
	 */
	@Override
	public void activeUser(String activeCode) {
		// TODO Auto-generated method stub
		userMapper.activeUser(activeCode);
	}

	/* (non-Javadoc)
	 * 根据账号获取用户信息
	 */
	@Override
	public String getUserByLoginName(String loginName) {
		// TODO Auto-generated method stub
		User user = userMapper.getUserByLoginName(loginName);
		return user == null ? "not" : "exist";
	}
	
	
    
}
