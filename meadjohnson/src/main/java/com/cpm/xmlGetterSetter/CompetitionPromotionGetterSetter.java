package com.cpm.xmlGetterSetter;

/**
 * Created by yadavendras on 23-12-2015.
 */
public class CompetitionPromotionGetterSetter {

    String id;
    String category_cd;
    String category;
    String company_cd;
    String company;
    String remark;
    String promotion;
    String sku;

    public String getCategory_cd() {
        return category_cd;
    }

    public void setCategory_cd(String category_cd) {
        this.category_cd = category_cd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getCompany_cd() {
        return company_cd;
    }

    public void setCompany_cd(String company_cd) {
        this.company_cd = company_cd;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
