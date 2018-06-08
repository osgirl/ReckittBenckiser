package com.cpm.GetterSetter;

import android.R.string;

public class StoreBean {

	private String storetypeid, regionid, categoryid, storeid, storename,
			checkout_status = "", storeaddress, visitdate, latitude, longitude,
			status, invoice_number, cheque_number, person_name, phone_number;

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getCheque_number() {
		return cheque_number;
	}

	public void setCheque_number(String cheque_number) {
		this.cheque_number = cheque_number;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	private String store_image;
	private String store_image_status;
	
	private String normal;
	private String special;

	public String getNormal() {
		return normal;
	}

	public void setNormal(String normal) {
		this.normal = normal;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getStore_image_status() {
		return store_image_status;
	}

	public void setStore_image_status(String store_image_status) {
		this.store_image_status = store_image_status;
	}

	public String getStore_image() {
		return store_image;
	}

	public void setStore_image(String store_image) {
		this.store_image = store_image;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	private String channel, classification, shelfs, layout, size;

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getShelfs() {
		return shelfs;
	}

	public void setShelfs(String shelfs) {
		this.shelfs = shelfs;
	}

	public String getStoretypeid() {
		return storetypeid;
	}

	public void setStoretypeid(String storetypeid) {
		this.storetypeid = storetypeid;
	}

	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckout_status() {
		return checkout_status;
	}

	public void setCheckout_status(String checkout_status) {
		this.checkout_status = checkout_status;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getVisitdate() {
		return visitdate;
	}

	public void setVisitdate(String visitdate) {
		this.visitdate = visitdate;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getStoreaddress() {
		return storeaddress;
	}

	public void setStoreaddress(String storeaddress) {
		this.storeaddress = storeaddress;
	}

}
