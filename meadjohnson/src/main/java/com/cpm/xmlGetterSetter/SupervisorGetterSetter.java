package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class SupervisorGetterSetter {
	
	String supervisortable;
	
	public String getSupervisortable() {
		return supervisortable;
	}
	public void setSupervisortable(String supervisortable) {
		this.supervisortable = supervisortable;
	}
	ArrayList<String> supervisorname = new ArrayList<String>();
	ArrayList<String> supervisorId = new ArrayList<String>();
	public ArrayList<String> getSupervisorname() {
		return supervisorname;
	}
	public void setSupervisorname(String supervisorname) {
		this.supervisorname.add(supervisorname);
	}
	public ArrayList<String> getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId.add(supervisorId);
	}
	
}
