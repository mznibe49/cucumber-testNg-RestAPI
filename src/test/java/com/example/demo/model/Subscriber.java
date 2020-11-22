package com.example.demo.model;

import java.util.Date;
import java.util.List;

public class Subscriber {

    private String firstName;
    private String lastName;
    private String location;
    private String address;
    private List<Contract> listContract;

    public Subscriber(String firstName, String lastName, String location, String address, List<Contract> listContract) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.address = address;
        this.listContract = listContract;
    }

    public void setAddressInAllContract(String s){
        for(int i = 0; i < listContract.size(); i++){
            Date tmp = listContract.get(i).getSubscriptionDate();
            listContract.set(i, new Contract(s,tmp));
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addContract(Contract c){
        this.listContract.add(c);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Contract> getListContract() {
        return listContract;
    }

    public void setListContract(List<Contract> listContract) {
        this.listContract = listContract;
    }
}
