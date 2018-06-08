package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class Sup_Merchandiser {
	
	String supMerchandiserTable;
	ArrayList<String> merchandiser = new ArrayList<String>();
	ArrayList<String> mandays = new ArrayList<String>();
	ArrayList<String> coverage = new ArrayList<String>();
	ArrayList<String> merchandised = new ArrayList<String>();
	ArrayList<String> compliance = new ArrayList<String>();
	
	
	public String getSupMerchandiserTable() {
		return supMerchandiserTable;
	}
	public void setSupMerchandiserTable(String supMerchandiserTable) {
		this.supMerchandiserTable = supMerchandiserTable;
	}
	public ArrayList<String> getMerchandiser() {
		return merchandiser;
	}
	public void setMerchandiser(String merchandiser) {
		this.merchandiser.add(merchandiser);
	}
	public ArrayList<String> getCoverage() {
		return coverage;
	}
	public void setCoverage(String coverage) {
		this.coverage.add(coverage);
	}
	public ArrayList<String> getMerchandised() {
		return merchandised;
	}
	public void setMerchandised(String merchandised) {
		this.merchandised.add(merchandised) ;
	}
	public ArrayList<String> getCompliance() {
		return compliance;
	}
	public void setCompliance(String compliance) {
		this.compliance.add(compliance) ;
	}
	public ArrayList<String> getMandays() {
		return mandays;
	}
	public void setMandays(String mandays) {
		this.mandays.add(mandays) ;
	}
	
	
}
