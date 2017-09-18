package com.batch.dto;

public class ReadItemDTO {
	
	private int id;
	private String itemName;
	private int itemNumber;
	private int itemGroupNumber;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public int getItemGroupNumber() {
		return itemGroupNumber;
	}
	public void setItemGroupNumber(int itemGroupNumber) {
		this.itemGroupNumber = itemGroupNumber;
	}
	

}
