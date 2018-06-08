package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class ColdroomClosingGetterSetter {
	
	public ArrayList<String> getStore_cd() {
		return store_cd;
	}
	public void setStore_cd(String store_cd) {
		this.store_cd.add(store_cd);
	}
	public ArrayList<String> getSku_cd() {
		return sku_cd;
	}
	public void setSku_cd(String sku_cd) {
		this.sku_cd.add(sku_cd);
	}
	public ArrayList<String> getClosing_cold() {
		return closing_cold;
	}
	public void setClosing_cold(String closing_cold) {
		this.closing_cold.add(closing_cold);
	}
	ArrayList<String> store_cd = new ArrayList<String>();
	ArrayList<String> sku_cd = new ArrayList<String>();
	ArrayList<String> closing_cold = new ArrayList<String>();
	
	String closing_cold_table;

	public String getClosing_cold_table() {
		return closing_cold_table;
	}
	public void setClosing_cold_table(String closing_cold_table) {
		this.closing_cold_table = closing_cold_table;
	}

}
