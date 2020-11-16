package com.example.myapplication;

public class Item {
    String businessId;
    String name;
    String registrationDate;
    String companyForm;

    public Item(String businessId, String name, String registrationDate, String companyForm) {
        this.businessId = businessId;
        this.name = name;
        this.registrationDate = registrationDate;
        this.companyForm = companyForm;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        name = name;
    }
}
