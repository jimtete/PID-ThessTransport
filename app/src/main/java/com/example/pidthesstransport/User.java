package com.example.pidthesstransport;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements ticketPurchase, PassPurchase {


    private int userID;

    private TicketHistory ticketHistory;
    private String firstName;
    private String lastName;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;
    private String phoneNumber;
    private String state;
    private String city;
    private String district;
    private int zipCode;
    private String address;

    @SerializedName("email")
    @Expose
    private String email;

    private Balance balance;


    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", zipCode=" + zipCode +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }

    public User(){}

    public User(String username, String password, String email) {
        this(null,null,username,password,null,null,null,null,-1,null,email,
                new Balance(-1.0,-1));
    }

    public User(String firstName, String lastName,
                String username, String password, String phoneNumber,
                String state, String city, String district,
                int zipCode, String address, String email, Balance balance) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.city = city;
        this.district = district;
        this.zipCode = zipCode;
        this.address = address;
        this.email = email;
        this.balance = balance;
        this.ticketHistory = new TicketHistory();
    }

    public void AddBalance(double amount){
        balance.setBalance(balance.getBalance()+amount);
    }


    public TicketHistory getTicketHistory() {
        return ticketHistory;
    }

    public void setTicketHistory(TicketHistory ticketHistory) {
        this.ticketHistory = ticketHistory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void ticketPurchase(double amount) {


        this.balance.setBalance(Math.round((this.balance.getBalance()-amount)*100.0)/100.0);
    }

    @Override
    public void BuyPass() {

    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }


}
