package com.eres.waiter.waiter.model;


public class Client{
	public Client(int clientType, String phones, Object address, int rating, int id, String name) {
		this.clientType = clientType;
		this.phones = phones;
		this.address = address;
		this.rating = rating;
		this.id = id;
		this.name = name;
	}

	private int clientType;

	private String phones;

	private Object address;

	private int rating;

	private int id;

	private String name;

	public void setClientType(int clientType){
		this.clientType = clientType;
	}

	public int getClientType(){
		return clientType;
	}

	public void setPhones(String phones){
		this.phones = phones;
	}

	public String getPhones(){
		return phones;
	}

	public void setAddress(Object address){
		this.address = address;
	}

	public Object getAddress(){
		return address;
	}

	public void setRating(int rating){
		this.rating = rating;
	}

	public int getRating(){
		return rating;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"Client{" + 
			"clientType = '" + clientType + '\'' + 
			",phones = '" + phones + '\'' + 
			",address = '" + address + '\'' + 
			",rating = '" + rating + '\'' + 
			",id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}