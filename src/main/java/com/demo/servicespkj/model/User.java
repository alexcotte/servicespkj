package com.demo.servicespkj.model;

public class User {
	
	String mail;
	String menber; //--> Afiliated
	String name;
	String lastname;
	String password;
	String phone;
	
	
	public User() {}
	
	public User(String mail, String menber, String name, String lastname, String password, String phone) {
		
		this.mail = mail;
		this.menber = menber;
		this.name = name;
		this.lastname = lastname;
		this.password = password;
		this.phone = phone;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMenber() {
		return menber;
	}
	public void setMenber(String menber) {
		this.menber = menber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
