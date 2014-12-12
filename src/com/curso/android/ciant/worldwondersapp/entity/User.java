package com.curso.android.ciant.worldwondersapp.entity;

public class User {

    private String email;
    private String name;
    private String password;
    private String country;
    private String language;
    private String gender;
    private Integer notification;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getNotification() {
		return notification;
	}
	public void setNotification(Integer notification) {
		this.notification = notification;
	}
}