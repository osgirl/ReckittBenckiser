package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class PromotionalMasterGettersetter {
	
	ArrayList<String> promotionid= new ArrayList<String>();
	ArrayList<String> promotionname = new ArrayList<String>();
	
	

	
	public ArrayList<String> getpromotionid() {
		return promotionid;
	}

	public void setpromotionid(String promotionid) {
		this.promotionid.add(promotionid);
	}

	
	
	
	public ArrayList<String> getpromotionname() {
		return promotionname;
	}

	public void setpromotionname(String promotionname) {
		this.promotionname.add(promotionname);
	}

	

	
	
	
	
	

}
