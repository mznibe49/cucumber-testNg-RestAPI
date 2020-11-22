package com.example.demo.model;

import java.util.Date;

public class Contract {

    private String address;
    private Date subscriptionDate;




    public Contract(String address, Date subscriptionDate) {
        this.address = address;
        this.subscriptionDate = subscriptionDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
