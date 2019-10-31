/**
 * 
 */
package com.longIt.shoppingApp.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.service.ArticleServiceI;
import com.longIt.shoppingApp.service.ArticleTypeServiceI;
import com.longIt.util.pager.PageModel;

/**
 * @author 罗老师【Long】
 * Version:1.0
 * 备注：本套课程提供全部源码+笔记+相关开发工具+答疑服务
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	 @Autowired   
	 private ArticleServiceI articleService;
	 
	 @Autowired   
	 private ArticleTypeServiceI articleTypeService;
	
	
	 //展示首页商品信息	
	 @RequestMapping("/getAll.action")
	 public String getAllArticles(Model model,String typeCode,String title,PageModel pageModel) {
		 
		 //根据商品类型以及商品的标题获取商品信息
		 List<Article> articles = articleService.getAllArticles(typeCode,title,pageModel);
		 model.addAttribute("articles", articles);
		 
		 model.addAttribute("pageModel", pageModel);
		 
		 
		 model.addAttribute("title", title);
		 model.addAttribute("typeCode", typeCode);
		 
		 
		 //获取所有的一级商品类型
		 List<ArticleType> articleTypes = articleTypeService.findAllFirstArticleType();
		 model.addAttribute("firstArticleTypes", articleTypes);
		 
		 return "article/list";
		 
	 }
	 
	 //展示商品明细信息	
	 @RequestMapping("/preArticle.action")
	 public String preArticle(Model model,Integer id) {
		 
		 //根据商品的id获取商品信息
		 Article article = articleService.getArticleById(id);
		 
		 model.addAttribute("article", article);
		 
		 return "article/preArticle";
		 
	 }
	 
	 //商品信息下架	
	 @RequestMapping("/removeArticle.action")
	 public String removeArticle(Model model,Integer id) {
		 
		 articleService.removeArticleById(id);
		 
		 return "redirect:/article/getAll.action";
		 
	 }
	 
	 //添加商品  
	 @RequestMapping("/addArticle.action")
	 public String addArticle(MultipartFile file,String code,Article article,HttpServletRequest request) {
		 
		
		 try {
			
			 InputStream input = file.getInputStream();
			 
			 //获取项目部署路径
			 String realPath =  request.getServletContext().getRealPath("/image/article");
			 
			 //如果上传所指定的目录不存在则创建目录
			 File f = new File(realPath);
			 if(!f.exists()) {
				 f.mkdirs();
			 }
			 
			 //获取当前上传的文件的名字   a.jpg
		     String imageName = file.getOriginalFilename();
		     //重新生成新的文件名
		     String  newFileName = UUID.randomUUID().toString() + imageName.substring(imageName.lastIndexOf("."), imageName.length());
		     System.out.println("文件路径:"+(realPath+File.separator+newFileName));
		     //构建输出流
		     OutputStream out = new FileOutputStream(realPath+File.separator+newFileName);
		     
		     byte[] data = new byte[1024];
		     
		     int len = 0;
		     
		     while((len = input.read(data)) != -1) {
		    	 out.write(data, 0, len);
		    	 out.flush();
		     }
		     
		     out.close();
		     input.close();
		     
		     //指定商品类型
		     article.setTypeCode(code);
		     
		     article.setImage(newFileName);
		     
		     //保存商品信息
		     articleService.saveArticle(article);
		     request.setAttribute("tip", "商品添加成功！");
		 
		 } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		     request.setAttribute("tip", "商品添加失败:"+e.getMessage());
		}
		 
		 return "redirect:/article/getAll.action";
		 
	 }
	 
	 //跳转至修改商品信息页面   
	 @RequestMapping("/showUpdate.action")
	 public String showUpdate(Model model,Integer id) {
		 
		 
		  //根据商品id获取商品信息
		  Article article = articleService.getArticleById(id);
		  model.addAttribute("article", article);
		  
		  //获取所有的一级商品类型
		  List<ArticleType> articleTypes = articleTypeService.findAllFirstArticleType();
		  model.addAttribute("types", articleTypes);
		 
		 return "article/updateArticle";
		 
	 }
	 
	 
	 //更新商品信息  
	 @RequestMapping("/updateArticle.action")
	 public String updateArticle(MultipartFile file,String code,Article article,HttpServletRequest request) {
		 
		
		 try {
			
			 InputStream input = file.getInputStream();
			 
			 //获取项目部署路径
			 String realPath =  request.getServletContext().getRealPath("/image/article");
			 
			 //如果上传所指定的目录不存在则创建目录
			 File f = new File(realPath);
			 if(!f.exists()) {
				 f.mkdirs();
			 }
			 
			 //获取当前上传的文件的名字   a.jpg
		     String imageName = file.getOriginalFilename();
		     if(imageName!=null && !imageName.equals("")) {
		    	  //重新生成新的文件名
			     String  newFileName = UUID.randomUUID().toString() + imageName.substring(imageName.lastIndexOf("."), imageName.length());
			     System.out.println("文件路径:"+(realPath+File.separator+newFileName));
			     //构建输出流
			     OutputStream out = new FileOutputStream(realPath+File.separator+newFileName);
			     
			     byte[] data = new byte[1024];
			     
			     int len = 0;
			     
			     while((len = input.read(data)) != -1) {
			    	 out.write(data, 0, len);
			    	 out.flush();
			     }
			     
			     out.close();
			     input.close();
			     
			     article.setImage(newFileName);

		     }
		   
		     //指定商品类型
		     article.setTypeCode(code);
		     
		     
		     //更新商品信息
		     articleService.updateArticle(article);
		     request.setAttribute("tip", "商品更新成功！");
		 
		 } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		     request.setAttribute("tip", "商品更新失败:"+e.getMessage());
		}
		 
		 return "redirect:/article/getAll.action";
		 
	 }
}
