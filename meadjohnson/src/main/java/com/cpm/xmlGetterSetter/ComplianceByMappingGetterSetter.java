package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class ComplianceByMappingGetterSetter {

	ArrayList<String> MappingComplianceId = new ArrayList<String>();
	ArrayList<String> MappingCompliancePromotion = new ArrayList<String>();

	ArrayList<String> CATEGORY_ID = new ArrayList<String>();
	ArrayList<String> store_typeid = new ArrayList<String>();
	ArrayList<String> region_id = new ArrayList<String>();

	public ArrayList<String> getCATEGORY_ID() {
		return CATEGORY_ID;
	}

	public void setCATEGORY_ID(String cATEGORY_ID) {
		CATEGORY_ID.add(cATEGORY_ID);
	}

	ArrayList<String> BRAND_CATEGORY_ID = new ArrayList<String>();

	public ArrayList<String> getBRAND_CATEGORY_ID() {
		return BRAND_CATEGORY_ID;
	}

	public void setBRAND_CATEGORY_ID(String cATEGORY_ID) {
		BRAND_CATEGORY_ID.add(cATEGORY_ID);
	}
	
	
	ArrayList<String> BRAND_CATEGORY = new ArrayList<String>();
	ArrayList<String> planogram_image = new ArrayList<String>();
	ArrayList<String> path = new ArrayList<String>();


	public ArrayList<String> getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path.add(path);
	}

	public ArrayList<String> getPlanogram_image() {
		return planogram_image;
	}

	public void setPlanogram_image(String planogram_image) {
		this.planogram_image.add(planogram_image);
	}

	public ArrayList<String> getBRAND_CATEGORY() {
		return BRAND_CATEGORY;
	}

	public void setBRAND_CATEGORY(String cATEGORY_ID) {
		BRAND_CATEGORY.add(cATEGORY_ID);
	}
	
	ArrayList<String> MONTH_HERO = new ArrayList<String>();

	public ArrayList<String> getMONTH_HERO() {
		return MONTH_HERO;
	}

	public void setMONTH_HERO(String cATEGORY_ID) {
		MONTH_HERO.add(cATEGORY_ID);
	}
	
	
	

	public ArrayList<String> getStore_typeid() {
		return store_typeid;
	}

	public void setStore_typeid(String store_typeid) {
		this.store_typeid.add(store_typeid);
	}

	public ArrayList<String> getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id.add(region_id);
	}

	public ArrayList<String> getMappingComplianceId() {
		return MappingComplianceId;
	}

	public void setMappingComplianceId(String MappingComplianceID) {
		this.MappingComplianceId.add(MappingComplianceID);
	}

	public ArrayList<String> getMappingCompliancePromotion() {
		return MappingCompliancePromotion;
	}

	public void setMappingCompliancePromotion(String MappingComplianceName) {
		this.MappingCompliancePromotion.add(MappingComplianceName);
	}

}
