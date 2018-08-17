package com.konnectus.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Document
public class User extends org.springframework.security.core.userdetails.User{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public User(String username, String password, List<SimpleGrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	/**
	 * Special constructor to create users with only ID populated. Needed in Groups and Events.
	 */
	public User(String id) {
		super("dummy","dummy",new ArrayList<SimpleGrantedAuthority>());
		this.id = id;
	}
	
	public User() {
		super("dummy","dummy",new ArrayList<SimpleGrantedAuthority>());
	}
	@Id
	protected String id;
	protected String name;
	protected String email;
	protected String mobile;
	protected Address address;
	protected int age;
	protected List<String> hobbies;
	protected Audit audit;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	public Audit getAudit() {
		return audit;
	}
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	
	
	
	
	
}
