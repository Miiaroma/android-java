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

    public Item(String businessId, String name, String registrationDate, String companyForm) {
        this.businessId = businessId;
        this.name = name;
        this.registrationDate = registrationDate;
        this.companyForm = companyForm;
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
        name = name;
    }

    public void setBusinessId(String businessId){
        businessId = businessId;
    }

    public void setRegistrationDate(String registrationDate){
        registrationDate = registrationDate;
    }

    public void setCompanyForm(String companyForm){
        companyForm = companyForm;
    }
}
