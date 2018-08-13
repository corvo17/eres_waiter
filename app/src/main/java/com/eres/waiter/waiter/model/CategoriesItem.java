package com.eres.waiter.waiter.model;

import java.util.List;

public class CategoriesItem{

	private int primaryCategoryId;

	private List<ProductsItem> products;

	private Object imageUrl;

	private int id;

	private int code;

	private String name;

	public void setPrimaryCategoryId(int primaryCategoryId){
		this.primaryCategoryId = primaryCategoryId;
	}

	public int getPrimaryCategoryId(){
		return primaryCategoryId;
	}

	public void setProducts(List<ProductsItem> products){
		this.products = products;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}

	public void setImageUrl(Object imageUrl){
		this.imageUrl = imageUrl;
	}

	public Object getImageUrl(){
		return imageUrl;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"CategoriesItem{" + 
			"primaryCategoryId = '" + primaryCategoryId + '\'' + 
			",products = '" + products + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",id = '" + id + '\'' + 
			",code = '" + code + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}