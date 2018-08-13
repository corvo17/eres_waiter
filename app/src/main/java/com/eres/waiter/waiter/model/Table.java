package com.eres.waiter.waiter.model;


public class Table{

	private int hallId;

	private Object description;

	private int id;

	private boolean isExtOrder;

	private int defaultWaiterId;

	private Object currentWaiterId;

	private String name;

	private int tableState;

	public void setHallId(int hallId){
		this.hallId = hallId;
	}

	public int getHallId(){
		return hallId;
	}

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public boolean isExtOrder() {
		return isExtOrder;
	}

	public void setExtOrder(boolean extOrder) {
		isExtOrder = extOrder;
	}

	public void setDefaultWaiterId(int defaultWaiterId){
		this.defaultWaiterId = defaultWaiterId;
	}

	public int getDefaultWaiterId(){
		return defaultWaiterId;
	}

	public void setCurrentWaiterId(Object currentWaiterId){
		this.currentWaiterId = currentWaiterId;
	}

	public Object getCurrentWaiterId(){
		return currentWaiterId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setTableState(int tableState){
		this.tableState = tableState;
	}

	public int getTableState(){
		return tableState;
	}

	@Override
 	public String toString(){
		return 
			"Table{" + 
			"hallId = '" + hallId + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",isExtOrder = '" + isExtOrder + '\'' + 
			",defaultWaiterId = '" + defaultWaiterId + '\'' + 
			",currentWaiterId = '" + currentWaiterId + '\'' + 
			",name = '" + name + '\'' + 
			",tableState = '" + tableState + '\'' + 
			"}";
		}
}