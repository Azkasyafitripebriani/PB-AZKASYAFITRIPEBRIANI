package com.example.helloworld.models;

public class UserDetails {
    private String userId, userName, userEmail, userPassword, userNIM;

    public UserDetails() { }

    public String getUserId() {
        return userId; }

    public void setUserId(String userId) {
        this.userId = userId; }

    public String getUserName() {
        return userName; }

    public void setUserName(String userName) {
        this.userName = userName; }

    public String getUserEmail() {
        return userEmail; }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail; }

    public String getUserPassword() {
        return userPassword; }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword; }

    public String getUserNIM() {
        return userNIM; }

    public void setUserNIM(String userNIM) {
        this.userNIM = userNIM; }

    public UserDetails(String userId, String userName, String userEmail, String userPassword, String userNIM) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail= userEmail;
        this.userPassword = userPassword;
        this.userNIM = userNIM;
    }
}
