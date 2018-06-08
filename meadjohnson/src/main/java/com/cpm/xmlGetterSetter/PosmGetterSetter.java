package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class PosmGetterSetter {
	
	
	ArrayList<String> Posm_id= new ArrayList<String>();
	ArrayList<String> posm = new ArrayList<String>();
	
	

	
	public ArrayList<String> getPosmID() {
		return Posm_id;
	}

	public void setPosmId(String ComplianceID) {
		this.Posm_id.add(ComplianceID);
	}

	
	
	public ArrayList<String> getPosm() {
		return posm;
	}

	public void setPosm(String ComplianceName) {
		this.posm.add(ComplianceName);
	}

	

}



