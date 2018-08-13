package com.eres.waiter.waiter.model;

import java.util.List;

public class OrderData{

	private int tableId;

	private String closedTime;

	private List<OrderItemsItem> orderItems;

	private String dateTimeOrder;

	private String numberOrder;

	private int cashierId;

	private int stateId;

	private String descriptor;

	private int id;

	public OrderData(int tableId, String closedTime, List<OrderItemsItem> orderItems, String dateTimeOrder, String numberOrder, int cashierId, int stateId, String descriptor, int id, int waiterId) {
		this.tableId = tableId;
		this.closedTime = closedTime;
		this.orderItems = orderItems;
		this.dateTimeOrder = dateTimeOrder;
		this.numberOrder = numberOrder;
		this.cashierId = cashierId;
		this.stateId = stateId;
		this.descriptor = descriptor;
		this.id = id;
		this.waiterId = waiterId;
	}

	private int waiterId;

	public void setTableId(int tableId){
		this.tableId = tableId;
	}

	public int getTableId(){
		return tableId;
	}

	public void setClosedTime(String closedTime){
		this.closedTime = closedTime;
	}

	public String getClosedTime(){
		return closedTime;
	}

	public void setOrderItems(List<OrderItemsItem> orderItems){
		this.orderItems = orderItems;
	}

	public List<OrderItemsItem> getOrderItems(){
		return orderItems;
	}

	public void setDateTimeOrder(String dateTimeOrder){
		this.dateTimeOrder = dateTimeOrder;
	}

	public String getDateTimeOrder(){
		return dateTimeOrder;
	}

	public void setNumberOrder(String numberOrder){
		this.numberOrder = numberOrder;
	}

	public String getNumberOrder(){
		return numberOrder;
	}

	public void setCashierId(int cashierId){
		this.cashierId = cashierId;
	}

	public int getCashierId(){
		return cashierId;
	}

	public void setStateId(int stateId){
		this.stateId = stateId;
	}

	public int getStateId(){
		return stateId;
	}

	public void setDescriptor(String descriptor){
		this.descriptor = descriptor;
	}

	public String getDescriptor(){
		return descriptor;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setWaiterId(int waiterId){
		this.waiterId = waiterId;
	}

	public int getWaiterId(){
		return waiterId;
	}

	@Override
 	public String toString(){
		return 
			"OrderData{" + 
			"tableId = '" + tableId + '\'' + 
			",closedTime = '" + closedTime + '\'' + 
			",orderItems = '" + orderItems + '\'' + 
			",dateTimeOrder = '" + dateTimeOrder + '\'' + 
			",numberOrder = '" + numberOrder + '\'' + 
			",cashierId = '" + cashierId + '\'' + 
			",stateId = '" + stateId + '\'' + 
			",descriptor = '" + descriptor + '\'' + 
			",id = '" + id + '\'' + 
			",waiterId = '" + waiterId + '\'' + 
			"}";
		}
}