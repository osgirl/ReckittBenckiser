package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class Sup_Window {
	
	String supWindowTable;
	ArrayList<String> cat_id = new ArrayList<String>();
	ArrayList<String> cat_name = new ArrayList<String>();
	ArrayList<String> brand_id = new ArrayList<String>();
	ArrayList<String> brand_name = new ArrayList<String>();
	public ArrayList<String> getCat_id() {
		return cat_id;
	}

	public void setCat_id(String cat_id) {
		this.cat_id.add(cat_id);
	}

	public ArrayList<String> getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name.add(cat_name);
	}

	public ArrayList<String> getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id.add(brand_id);
	}

	public ArrayList<String> getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name.add(brand_name);
	}

	public String getSupWindowTable() {
		return supWindowTable;
	}

	public void setSupWindowTable(String supWindowTable) {
		this.supWindowTable = supWindowTable;
	}

}
