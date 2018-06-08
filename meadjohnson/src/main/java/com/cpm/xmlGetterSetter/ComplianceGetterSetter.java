package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class ComplianceGetterSetter {
	
	
	ArrayList<String> ComplianceId= new ArrayList<String>();
	ArrayList<String> ComplianceName = new ArrayList<String>();
	
	

	
	public ArrayList<String> getComplianceId() {
		return ComplianceId;
	}

	public void setComplianceID(String ComplianceID) {
		this.ComplianceId.add(ComplianceID);
	}

	
	
	public ArrayList<String> getComplianceName() {
		return ComplianceName;
	}

	public void setComplianceName(String ComplianceName) {
		this.ComplianceName.add(ComplianceName);
	}

	

}



