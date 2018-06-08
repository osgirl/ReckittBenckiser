package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class SkuMappingGetterSetter {
	
	
	ArrayList<String> sku_id= new ArrayList<String>();
	ArrayList<String>storetype_id  = new ArrayList<String>();
	ArrayList<String> REGION_CD= new ArrayList<String>();
	ArrayList<String>SKU_SEQUENCE  = new ArrayList<String>();
	ArrayList<String>core_sku  = new ArrayList<String>();
	public ArrayList<String> getCore_sku() {
		return core_sku;
	}
	public void setCore_sku(String core_sku) {
		this.core_sku.add(core_sku);
	}
	public ArrayList<String> getSku_id() {
		return sku_id;
	}
	public void setSku_id(String sku_id) {
		this.sku_id.add(sku_id);
	}
	public ArrayList<String> getStoretype_id() {
		return storetype_id;
	}
	public void setStoretype_id(String storetype_id) {
		this.storetype_id.add(storetype_id);
	}
	public ArrayList<String> getREGION_CD() {
		return REGION_CD;
	}
	public void setREGION_CD(String rEGION_CD) {
		REGION_CD.add(rEGION_CD);
	}
	public ArrayList<String> getSKU_SEQUENCE() {
		return SKU_SEQUENCE;
	}
	public void setSKU_SEQUENCE(String sKU_SEQUENCE) {
		SKU_SEQUENCE.add(sKU_SEQUENCE);
	}
	
	




}
