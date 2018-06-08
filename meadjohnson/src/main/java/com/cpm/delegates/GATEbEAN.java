package com.cpm.delegates;

import android.R.integer;

public class GATEbEAN {

	private String CITYID, CITY, LOC, NAME, EID, present, status, commonID,
			ntPresentReason, TIME, attendanceid, date,desigid,image1,image2,image3;

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getDesigid() {
		return desigid;
	}

	public void setDesigid(String desigid) {
		this.desigid = desigid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private int attendance_position;

	public int getAttendance_position() {
		return attendance_position;
	}

	public void setAttendance_position(Integer attendance_position) {
		this.attendance_position = attendance_position;
	}

	public String getAttendanceid() {
		return attendanceid;
	}

	public void setAttendanceid(String attendanceid) {
		this.attendanceid = attendanceid;
	}

	public String getTIME() {
		return TIME;
	}

	public void setTIME(String tIME) {
		TIME = tIME;
	}

	public String getNtPresentReason() {
		return ntPresentReason;
	}

	public void setNtPresentReason(String ntPresentReason) {
		this.ntPresentReason = ntPresentReason;
	}

	public String getCommonID() {
		return commonID;
	}

	public void setCommonID(String commonID) {
		this.commonID = commonID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public String getCITYID() {
		return CITYID;
	}

	public void setCITYID(String cITYID) {
		CITYID = cITYID;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getLOC() {
		return LOC;
	}

	public void setLOC(String lOC) {
		LOC = lOC;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getEID() {
		return EID;
	}

	public void setEID(String eID) {
		EID = eID;
	}

}
