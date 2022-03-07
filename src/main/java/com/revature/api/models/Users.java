package com.revature.api.models;

import java.util.Objects;

public class Users {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String givenName;
    private String surname;
    private Boolean isActive;
    private UserRoles userRole;

    public Users(){

        super();
    }

    public Users(String userId, String username, String email, String password, String givenName, String surname, Boolean isActive, UserRoles userRole){
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
        this.userRole = userRole;
    }

    public Users(String username, String email, String password, String given_name, String surname) {
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserName(){
        return username;
    }



    public void setUserName(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;

    }

    public String getPassword(){
        this.password = password;
        return password;
    }

    public void setPassword(String password){
        this.password = password;

    }

    public String getGivenName(){
        return givenName;
    }

    public void setGivenName(String givenName){
        this.givenName = givenName;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public Boolean getIsActive(){
        return isActive;
    }

    public void setIsActive(Boolean isActive){
        this.isActive = isActive;
    }



    public UserRoles getUserRole(){
        return userRole;
    }

    public void setUserRole(UserRoles userRole){
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users user = (Users) o;
        return isActive == user.isActive && Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(givenName, user.givenName) && Objects.equals(surname, user.surname) && Objects.equals(userRole, user.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, password, givenName, surname, isActive, userRole);
    }



    @Override
    public String toString() {
        return "users{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", giveName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", isActive=" + isActive +
                ", userRole='" + userRole + '\'' +
                '}';
    }

}
