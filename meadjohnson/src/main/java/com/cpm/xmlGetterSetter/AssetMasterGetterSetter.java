package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class AssetMasterGetterSetter {
	
	String asset_master_table;
	
	ArrayList<String> asset_cd=new ArrayList<String>();
	ArrayList<String> asset=new ArrayList<String>();
	

	public ArrayList<String> getAsset_cd() {
		return asset_cd;
	}

	public void setAsset_cd(String asset_cd) {
		this.asset_cd.add(asset_cd);
	}

	public ArrayList<String> getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset.add(asset);
	}
	
	public String getAsset_master_table() {
		return asset_master_table;
	}

	public void setAsset_master_table(String asset_master_table) {
		this.asset_master_table = asset_master_table;
	}

}
