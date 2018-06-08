package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class MerchndiserGetterSetter {
	
	
	ArrayList<String> status = new ArrayList<String>();
	public ArrayList<String> getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status.add(status);
	}
	public ArrayList<String> getmerc_id() {
		return merc_id;
	}
	public void setmerc_id(String merc_id) {
		this.merc_id.add(merc_id);
	}
	public ArrayList<String> getmerc_name() {
		return merc_name;
	}
	public void setmerc_name(String merc_name) {
		this.merc_name.add(merc_name);
	}


	

	
	
	public String getMeta_data() {
		return meta_data;
	}
	public void setMeta_data(String meta_data) {
		this.meta_data = meta_data;
	}
	
	ArrayList<String> merc_id = new ArrayList<String>();
	ArrayList<String> merc_name = new ArrayList<String>();
	
	ArrayList<String> mer_score = new ArrayList<String>();
	
	public ArrayList<String> getMer_score() {
		return mer_score;
	}
	public void setMer_score(String mer_score) {
		this.mer_score.add(mer_score);
	}

	String meta_data;
	
}
