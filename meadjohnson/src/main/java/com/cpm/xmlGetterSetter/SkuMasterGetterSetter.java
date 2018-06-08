package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class SkuMasterGetterSetter {

	String sku_master_table;
	
	public String getSku_master_table() {
		return sku_master_table;
	}


	public void setSku_master_table(String sku_master_table) {
		this.sku_master_table = sku_master_table;
	}


	ArrayList<String> sku_cd = new ArrayList<String>();
	ArrayList<String> sku = new ArrayList<String>();
	ArrayList<String> brand_cd = new ArrayList<String>();
	ArrayList<String> brand = new ArrayList<String>();
	ArrayList<String> category_cd = new ArrayList<String>();
	ArrayList<String> category = new ArrayList<String>();
	ArrayList<String> mrp = new ArrayList<String>();
	/*ArrayList<String> category_type = new ArrayList<String>();
	ArrayList<String> packing_size = new ArrayList<String>();*/
	
	ArrayList<String> sku_sequence = new ArrayList<String>();
	ArrayList<String> brand_sequence = new ArrayList<String>();
	ArrayList<String> category_sequence = new ArrayList<String>();
	

	/*public ArrayList<String> getPacking_size() {
		return packing_size;
	}


	public void setPacking_size(String packing_size) {
		this.packing_size.add(packing_size);
	}*/


	public ArrayList<String> getSku_cd() {
		return sku_cd;
	}


	public void setSku_cd(String sku_cd) {
		this.sku_cd.add(sku_cd);
	}
	


	public ArrayList<String> getSku() {
		return sku;
	}


	public void setSku(String sku) {
		this.sku.add(sku);
	}


	public ArrayList<String> getBrand_cd() {
		return brand_cd;
	}


	public void setBrand_cd(String brand_cd) {
		this.brand_cd.add(brand_cd);
	}


	public ArrayList<String> getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand.add(brand);
	}


	public ArrayList<String> getCategory_cd() {
		return category_cd;
	}


	public void setCategory_cd(String category_cd) {
		this.category_cd.add(category_cd);
	}


/*	public ArrayList<String> getCategory_type() {
		return category_type;
	}


	public void setCategory_type(String category_type) {
		this.category_type.add(category_type);
	}*/
	
	public ArrayList<String> getCategory() {
		return category;
	}


	public ArrayList<String> getSku_sequence() {
		return sku_sequence;
	}


	public void setSku_sequence(String sku_sequence) {
		this.sku_sequence.add(sku_sequence);
	}


	public ArrayList<String> getBrand_sequence() {
		return brand_sequence;
	}


	public void setBrand_sequence(String brand_sequence) {
		this.brand_sequence.add(brand_sequence);
	}


	public ArrayList<String> getCategory_sequence() {
		return category_sequence;
	}


	public void setCategory_sequence(String category_sequence) {
		this.category_sequence.add(category_sequence);
	}


	public void setCategory(String category) {
		this.category.add(category);
	}


	public ArrayList<String> getMrp() {
		return mrp;
	}


	public void setMrp(String mrp) {
		this.mrp.add(mrp);
	}


}
