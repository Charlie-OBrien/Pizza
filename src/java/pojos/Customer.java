/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author charl
 */
public class Customer {
    private int CustomerID;
    
    private String firstName;
    private String lastName;
    
    private String address;
    private int house;
    
    private String postalCode;
    private String province;
    
    private String phoneNumber;
    private String email;

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer() {
        //keep as is
    }

    public Customer(String firstName, String lastName, String address, int house, String postalCode, String province, String phoneNumber, String email) {
        //DO NOT ADD ID
        
        this.firstName = firstName;
        this.lastName = lastName;
        
        this.address = address;
        this.house = house;     //may cause issues with alpha numeric apartments KEEP FOR TIME BEING
        
        this.postalCode = postalCode;
        this.province = province;
        
        this.phoneNumber = phoneNumber;
        this.email = email;
    }



    
}
