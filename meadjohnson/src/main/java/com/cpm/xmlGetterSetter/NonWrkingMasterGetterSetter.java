package com.cpm.xmlGetterSetter;

import java.util.ArrayList;




public class NonWrkingMasterGetterSetter {
	
	ArrayList<String> Non_WorkingMasterReasonid= new ArrayList<String>();
	ArrayList<String> Non_WorkingReasonMastername = new ArrayList<String>();
	ArrayList<String> Non_WorkingReasonValid = new ArrayList<String>();
	
	public ArrayList<String> getSubReason_id() {
		return SubReason_id;
	}

	public void setSubReason_id(String subReason_id) {
		SubReason_id.add(subReason_id);
	}

	public ArrayList<String> getSub_reason() {
		return sub_reason;
	}

	public void setSub_reason(String sub_reason) {
		this.sub_reason.add(sub_reason);
	}

	ArrayList<String> SubReason_id = new ArrayList<String>();
	ArrayList<String> sub_reason = new ArrayList<String>();
	
	

	
	public ArrayList<String> getNon_WorkingReasonValid() {
		return Non_WorkingReasonValid;
	}

	public void setNon_WorkingReasonValid(String non_WorkingReasonValid) {
		this.Non_WorkingReasonValid.add(non_WorkingReasonValid);
	}

	public ArrayList<String> getNonWorkingReasonid() {
		return Non_WorkingMasterReasonid;
	}

	public void setNon_WorkingReasonId(String Non_WorkingMasterReasonid) {
		this.Non_WorkingMasterReasonid.add(Non_WorkingMasterReasonid);
	}

	
	
	public ArrayList<String> getNonWorkingReasonName() {
		return Non_WorkingReasonMastername;
	}

	public void setNon_WorkingReasonName(String Non_WorkingMasterReasonName) {
		this.Non_WorkingReasonMastername.add(Non_WorkingMasterReasonName);
	}


	String nonworkingtable;
	

	public String getNonworkingtable() {
		return nonworkingtable;
	}

	public void setNonworkingtable(String nonworkingtable) {
		this.nonworkingtable = nonworkingtable;
	}
	ArrayList<String> imageAllow = new ArrayList<String>();
	public ArrayList<String> getImageAllow() {
		return imageAllow;
	}

	public void setImageAllow(String imageAllow) {
		this.imageAllow.add(imageAllow);
	}

	public ArrayList<String> getEntryAlow() {
		return entryAlow;
	}

	public void setEntryAlow(String entryAlow) {
		this.entryAlow.add(entryAlow);
	}

	ArrayList<String> entryAlow = new ArrayList<String>();
}
