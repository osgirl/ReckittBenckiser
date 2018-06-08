package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class StoreListGetterSetter {
	
	ArrayList<String>  storeid = new ArrayList<String>();
	ArrayList<String>  storename = new ArrayList<String>();
	ArrayList<String>  city = new ArrayList<String>();
	ArrayList<String>  score = new ArrayList<String>();
	ArrayList<String>  status = new ArrayList<String>();
	
	public ArrayList<String> getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status.add(status);
	}
	ArrayList<String>  merchandiserId = new ArrayList<String>();
	public ArrayList<String> getMerchandiserId() {
		return merchandiserId;
	}
	public void setMerchandiserId(String merchandiserId) {
		this.merchandiserId.add(merchandiserId);
	}
	public ArrayList<String> getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category.add(category);
	}
	public ArrayList<String> getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType.add(storeType);
	}
	ArrayList<String>  category = new ArrayList<String>();
	ArrayList<String>  storeType = new ArrayList<String>();
	 public ArrayList<String> getScore() {
		return score;
	}
	public void setScore(String beatName) {
		this.score.add(beatName);
	}
	String storeTable ;
	
	public String getStoreTable() {
		return storeTable;
	}
	public void setStoreTable(String storeTable) {
		this.storeTable = storeTable;
	}
	String checkData;
	public String getCheckData() {
		return checkData;
	}
	public void setCheckData(String checkData) {
		this.checkData = checkData;
	}
	public ArrayList<String> getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid.add(storeid);
	}
	public ArrayList<String> getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename.add(storename);
	}
	public ArrayList<String> getCity() {
		return city;
	}
	public void setCity(String address) {
		this.city.add(address);
	}
	

}
