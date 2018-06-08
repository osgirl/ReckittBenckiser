package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class PerformanceGetterSetter {
	
	public ArrayList<String> getStore_cd() {
		return store_cd;
	}
	public void setStore_cd(String store_cd) {
		this.store_cd.add(store_cd);
	}
	public ArrayList<String> getMonthly_target() {
		return monthly_target;
	}
	public void setMonthly_target(String monthly_target) {
		this.monthly_target.add(monthly_target);
	}
	public ArrayList<String> getMtd_sales() {
		return mtd_sales;
	}
	public void setMtd_sales(String mtd_sales) {
		this.mtd_sales.add(mtd_sales);
	}
	public ArrayList<String> getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement.add(achievement);
	}
	public String getPerformance_table() {
		return performance_table;
	}
	public void setPerformance_table(String performance_table) {
		this.performance_table = performance_table;
	}
	public String performance_table;
	
	ArrayList<String> store_cd = new ArrayList<String>();
	ArrayList<String> monthly_target = new ArrayList<String>();
	ArrayList<String> mtd_sales = new ArrayList<String>();
	ArrayList<String> achievement = new ArrayList<String>();
	
	
	

	
}
