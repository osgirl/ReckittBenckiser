package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by yadavendras on 04-01-2016.
 */
public class CompanyGetterSetter {

    String company_master_table;

    ArrayList<String> company_cd=new ArrayList<String>();
    ArrayList<String> company=new ArrayList<String>();


    public String getCompany_master_table() {
        return company_master_table;
    }

    public void setCompany_master_table(String company_master_table) {
        this.company_master_table = company_master_table;
    }

    public ArrayList<String> getCompany_cd() {
        return company_cd;
    }

    public void setCompany_cd(String company_cd) {
        this.company_cd.add(company_cd);
    }

    public ArrayList<String> getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company.add(company);
    }
}
