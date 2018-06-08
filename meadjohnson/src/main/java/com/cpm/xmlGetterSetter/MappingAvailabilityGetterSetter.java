package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class MappingAvailabilityGetterSetter {
	
String mapping_avail_table;
	
	public String getMapping_avail_table() {
		return mapping_avail_table;
	}


	public void setMapping_avail_table(String mapping_avail_table) {
		this.mapping_avail_table = mapping_avail_table;
	}


	ArrayList<String> store_cd = new ArrayList<String>();
	ArrayList<String> sku_cd = new ArrayList<String>();
/*	ArrayList<String> category_sequence = new ArrayList<String>();
	ArrayList<String> brand_sequence = new ArrayList<String>();
	ArrayList<String> sku_sequence = new ArrayList<String>();
*/
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


	/*public ArrayList<String> getCategory_sequence() {
		return category_sequence;
	}


	public void setCategory_sequence(String category_sequence) {
		this.category_sequence.add(category_sequence);
	}


	public ArrayList<String> getBrand_sequence() {
		return brand_sequence;
	}


	public void setBrand_sequence(String brand_sequence) {
		this.brand_sequence.add(brand_sequence);
	}


	public ArrayList<String> getSku_sequence() {
		return sku_sequence;
	}


	public void setSku_sequence(String sku_sequence) {
		this.sku_sequence.add(sku_sequence);
	}*/

	
}
