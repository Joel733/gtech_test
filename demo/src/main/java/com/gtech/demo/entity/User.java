package com.gtech.demo.entity;

import java.sql.Timestamp;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Date   birthdate;
    private String logintoken;
    // @Column(unique=true)
    private String email;
    private String resetpassToken;
    private String lastLogin;
    public User(){

    }
    public User(int userId, String username, String password, String firstname, String lastname, Date birthdate,
    String logintoken, String email, String resetpassToken, String lastLogin){
        this.userId = userId; 
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.logintoken = logintoken;
		this.email = email;
		this.resetpassToken = resetpassToken;
		this.lastLogin = lastLogin;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
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
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public String getLogintoken() {
        return logintoken;
    }
    public void setLogintoken(String logintoken) {
        this.logintoken = logintoken;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getResetpassToken() {
        return resetpassToken;
    }
    public void setResetpassToken(String resetpassToken) {
        this.resetpassToken = resetpassToken;
    }
    public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
}
