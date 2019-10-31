/**
 * 
 */
package com.longIt.shoppingApp.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.service.ArticleServiceI;
import com.longIt.shoppingApp.service.ArticleTypeServiceI;
import com.longIt.util.pager.PageModel;

import net.sf.json.JSONArray;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
@Controller
@RequestMapping("/articleType")
public class ArticleTypeController {
	
	 
	 @Autowired   
	 private ArticleTypeServiceI articleTypeService;
	 
	 @Autowired   
	 private ArticleServiceI articleService;
	
	
	 //获取所有的商品类型信息
	 @RequestMapping("/getAll.action")
	 public String getAll(Model model,PageModel pageModel,String message) {
		 
		 //根据商品类型以及商品的标题获取商品信息
		 List<ArticleType> articleTypes = articleTypeService.getAllArticleTypes(pageModel);
		 model.addAttribute("list", articleTypes);
		 
		 if(message != null && !message.equals("")) {
			 model.addAttribute("message", URLDecoder.decode(message));
		 }
		 

		 return "articleType/list";
		 
	 }
	 
	 
	 //加载修改商品类型页面  
	 @RequestMapping("/showUpdate.action")
	 public String showUpdate(Model model,String code) {
		 
		ArticleType type = articleTypeService.getArticleTypeByCode(code);
		model.addAttribute("articleType", type);

		 return "articleType/view";
		 
	 }
	 
	 
	 //加载添加商品类型页面  
	 @RequestMapping("/showAdd.action")
	 public String showAdd() {
		 
		 return "articleType/view";
		 
	 }
	 
	 
	 //通过异步请求的方式获取所有一级商品类型   
	 @ResponseBody
	 @RequestMapping(value="/loadFirstArticleType.action",produces= {"application/json;charset=utf-8"})
	 public String loadFirstArticleType() {
		 
		 List<ArticleType> types = articleTypeService.findAllFirstArticleType();
          System.out.println("types:"+JSONArray.fromObject(types).toString());
		 return JSONArray.fromObject(types).toString();
		 
	 }
	 
	 
	 //商品类型的添加或者修改操作   
	 @RequestMapping("/addOrUpdate.action")
	 public String addOrUpdate(ArticleType type,String  parentCode) {
		 
		 if(type.getCode() !=null && !type.getCode().equals("")) {
			 //进行更新商品类型操作
			 articleTypeService.updateArticleType(type);
			 
		 }else {
			 //进行添加商品类型操作
			 articleTypeService.saveArticleType(type,parentCode);
		 }

		 return "redirect:/articleType/getAll.action";
		 
	 }
	 
	 //删除商品类型
	 @RequestMapping("/deleteType.action")
	 public String deleteType(@Param("code")String  code) {
		 
		 String message = articleTypeService.deleteType(code);
		 return "redirect:/articleType/getAll.action?message="+URLEncoder.encode(message);
		 
	 }
}
