package com.deepak.passwdapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.deepak.passwdapi.domain.Group;
import com.deepak.passwdapi.domain.User;

@Component
@ConfigurationProperties("passwdfile")
public class AppPropertiesPasswd {

	private List<User> users = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
    
    
}
