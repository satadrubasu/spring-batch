package com.batch.dto;

public class WriteItemDTO {
	
	private String itemAlias;
	private String itemName;
	private int itemNumber;
	private int itemGroupNumber;
	
	
	public String getItemAlias() {
		return itemAlias;
	}
	public void setItemAlias(String itemAlias) {
		this.itemAlias = itemAlias;
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
