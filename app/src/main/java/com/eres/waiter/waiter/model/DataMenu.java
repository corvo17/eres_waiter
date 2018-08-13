package com.eres.waiter.waiter.model;

import java.util.List;

public class DataMenu{

	private List<CategoriesItem> categories;

	private Object imageUrl;

	private int id;

	private int code;

	private String name;

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
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
			"DataMenu{" +
			"categories = '" + categories + '\'' +
			",imageUrl = '" + imageUrl + '\'' +
			",id = '" + id + '\'' +
			",code = '" + code + '\'' +
			",name = '" + name + '\'' +
			"}";
		}
}