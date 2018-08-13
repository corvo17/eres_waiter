package com.eres.waiter.waiter.model;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("msg")
	private String msg;

	@SerializedName("status")
	private String status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"msg = '" + msg + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}