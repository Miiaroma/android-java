package com.example.myapplication;

public class Item {
    String businessId;
    String name;
    String registrationDate;
    String companyForm;

    public Item()
    {
        this.businessId = "";
        this.name = "";
        this.registrationDate = "";
        this.companyForm = "";
    }

    public String getName(){
        return name;
    }

    public String getBusinessId(){
        return businessId;
    }

    public String getRegistrationDate(){
        return registrationDate;
    }

    public String getCompanyForm(){
        return companyForm;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBusinessId(String businessId){
        this.businessId = businessId;
    }

    public void setRegistrationDate(String registrationDate){
        this.registrationDate = registrationDate;
    }

    public void setCompanyForm(String companyForm){
        this.companyForm = companyForm;
    }
}
