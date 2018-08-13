package com.eres.waiter.waiter.model;

import java.util.List;

public class EmptyTable{

	private Object description;

	private List<TablesItem> tables;

	private int id;

	private int hallState;

	private int managerId;

	private String name;

	public EmptyTable(Object description, List<TablesItem> tables, int id, int hallState, int managerId, String name) {
		this.description = description;
		this.tables = tables;
		this.id = id;
		this.hallState = hallState;
		this.managerId = managerId;
		this.name = name;
	}

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setTables(List<TablesItem> tables){
		this.tables = tables;
	}

	public List<TablesItem> getTables(){
		return tables;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setHallState(int hallState){
		this.hallState = hallState;
	}

	public int getHallState(){
		return hallState;
	}

	public void setManagerId(int managerId){
		this.managerId = managerId;
	}

	public int getManagerId(){
		return managerId;
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
			"EmptyTable{" + 
			"description = '" + description + '\'' + 
			",tables = '" + tables + '\'' + 
			",id = '" + id + '\'' + 
			",hallState = '" + hallState + '\'' + 
			",managerId = '" + managerId + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}