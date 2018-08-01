package com.konnectus.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Group {
	
	protected String id;
	protected String name;
	protected String description;
	protected List<User> groupMembers;
	protected List<User> groupAdmin;
	
	public List<User> getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(List<User> groupMembers) {
		this.groupMembers = groupMembers;
	}
	
	
	public List<User> getGroupAdmin() {
		return groupAdmin;
	}
	public void setGroupAdmin(List<User> groupAdmin) {
		this.groupAdmin = groupAdmin;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
