package com.cpm.xmlGetterSetter;

import java.util.ArrayList;



public class DesignationGetterSetter {
	
	String tabledesig;
	
	public String getTabledesig() {
		return tabledesig;
	}
	public void setTabledesig(String tabledesig) {
		this.tabledesig = tabledesig;
	}
	ArrayList<String> desigId = new  ArrayList<String>();
	ArrayList<String> desig = new  ArrayList<String>();
	public ArrayList<String> getDesigId() {
		return desigId;
	}
	public void setDesigId(String desigId) {
		this.desigId.add(desigId);
	}
	public ArrayList<String> getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig.add(desig);
	}
	
}
