package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class MappingPromotionGetterSetter {
	
String mapping_promotion_table;
	
	public String getMapping_promotion_table() {
		return mapping_promotion_table;
	}


	public void setMapping_promotion_table(String mapping_promotion_table) {
		this.mapping_promotion_table = mapping_promotion_table;
	}
	
	ArrayList<String> store_cd = new ArrayList<String>();
	ArrayList<String> sku_cd = new ArrayList<String>();
	ArrayList<String> promotion = new ArrayList<String>();
	ArrayList<String> brand_sequence = new ArrayList<String>();
	ArrayList<String> sku_sequence = new ArrayList<String>();
	ArrayList<String> category_type = new ArrayList<String>();

	ArrayList<String> brand_cd = new ArrayList<String>();

	ArrayList<String> pid = new ArrayList<>();
	

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


	public ArrayList<String> getPromotion() {
		return promotion;
	}


	public void setPromotion(String promotion) {
		this.promotion.add(promotion);
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
	}
	
	public ArrayList<String> getCategory_type() {
		return category_type;
	}


	public void setCategory_type(String category_type) {
		this.category_type.add(category_type);
	}


	public ArrayList<String> getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid.add(pid);
	}

	public ArrayList<String> getBrand_cd() {
		return brand_cd;
	}

	public void setBrand_cd(String brand_cd) {
		this.brand_cd.add(brand_cd);
	}
}


