package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class Deviation_Reason {
String tabledeviation;
	
	public String gettabledeviation() {
		return tabledeviation;
	}
	public void settabledeviation(String tablemerchJourney) {
		this.tabledeviation = tablemerchJourney;
	}
	ArrayList<String> devi_id = new ArrayList<String>();
	ArrayList<String> devi_name = new ArrayList<String>();
	public ArrayList<String> getdevi_id() {
		return devi_id;
	}
	public void setdevi_id(String merchanId) {
		this.devi_id.add(merchanId);
	}
	public ArrayList<String> getdevi_name() {
		return devi_name;
	}
	public void setdevi_name(String visitdate) {
		this.devi_name.add(visitdate);
	}
}
