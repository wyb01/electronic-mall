package com.longIt.shoppingApp.bean;

/**
 * Shopcar 数据传输类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-05-05 21:31:40
 * @version 1.0
 */
public class Shopcar implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int buynum;
	private int userId;
	private int articleId;
	
	private Article article;

	/** setter and getter method */
	public void setBuynum(int buynum){
		this.buynum = buynum;
	}
	public int getBuynum(){
		return this.buynum;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	public int getUserId(){
		return this.userId;
	}
	public void setArticleId(int articleId){
		this.articleId = articleId;
	}
	public int getArticleId(){
		return this.articleId;
	}
	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}
	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}
	
	

}