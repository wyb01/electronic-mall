package com.longIt.shoppingApp.bean;

/**
 * ArticleType 数据传输类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-08-04 22:03:23
 * @version 1.0
 */
public class ArticleType implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String remark;

	/** setter and getter method */
	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return this.code;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}

}