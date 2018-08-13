package com.eres.waiter.waiter.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderItemsItem{
	public OrderItemsItem(int id, int productId, int orderId, int factCount, int departmentId, int stateId, int count, List<Object> orderItemSpecialDesires, int orderPartId, Object descriptor) {
		this.id = id;
		this.productId = productId;
		this.orderId = orderId;
		this.factCount = factCount;
		this.departmentId = departmentId;
		this.stateId = stateId;
		this.count = count;
		this.orderItemSpecialDesires = orderItemSpecialDesires;
		this.orderPartId = orderPartId;
		this.descriptor = descriptor;
	}

	@SerializedName("id")
	private int id;

	@SerializedName("productId")
	private int productId;

	@SerializedName("orderId")
	private int orderId;

	@SerializedName("factCount")
	private int factCount;

	@SerializedName("departmentId")
	private int departmentId;

	@SerializedName("stateId")
	private int stateId;

	@SerializedName("count")
	private int count;

	@SerializedName("orderItemSpecialDesires")
	private List<Object> orderItemSpecialDesires;

	@SerializedName("orderPartId")
	private int orderPartId;

	@SerializedName("descriptor")
	private Object descriptor;

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setOrderId(int orderId){
		this.orderId = orderId;
	}

	public int getOrderId(){
		return orderId;
	}

	public void setFactCount(int factCount){
		this.factCount = factCount;
	}

	public int getFactCount(){
		return factCount;
	}

	public void setDepartmentId(int departmentId){
		this.departmentId = departmentId;
	}

	public int getDepartmentId(){
		return departmentId;
	}

	public void setStateId(int stateId){
		this.stateId = stateId;
	}

	public int getStateId(){
		return stateId;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setOrderItemSpecialDesires(List<Object> orderItemSpecialDesires){
		this.orderItemSpecialDesires = orderItemSpecialDesires;
	}

	public List<Object> getOrderItemSpecialDesires(){
		return orderItemSpecialDesires;
	}

	public void setOrderPartId(int orderPartId){
		this.orderPartId = orderPartId;
	}

	public int getOrderPartId(){
		return orderPartId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDescriptor(Object descriptor){
		this.descriptor = descriptor;
	}

	public Object getDescriptor(){
		return descriptor;
	}

	@Override
 	public String toString(){
		return 
			"OrderItemsItem1{" + 
			"productId = '" + productId + '\'' + 
			",orderId = '" + orderId + '\'' + 
			",factCount = '" + factCount + '\'' + 
			",departmentId = '" + departmentId + '\'' + 
			",stateId = '" + stateId + '\'' + 
			",count = '" + count + '\'' + 
			",orderItemSpecialDesires = '" + orderItemSpecialDesires + '\'' + 
			",orderPartId = '" + orderPartId + '\'' + 
			",id = '" + id + '\'' + 
			",descriptor = '" + descriptor + '\'' + 
			"}";
		}
}