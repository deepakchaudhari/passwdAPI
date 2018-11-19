package com.deepak.passwdapi.repository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deepak.passwdapi.AppPropertiesPasswd;
import com.deepak.passwdapi.domain.User;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public class UserRepository  {

	private AppPropertiesPasswd app;
	
	@Autowired
    public void setApp(AppPropertiesPasswd app) {
        this.app = app;
    }

    
    public List<User> getAllUsers() {
        Collection<User> c = app.getUsers();
        List<User> list = new ArrayList<User>();
        list.addAll(c);
        return list;
    }
    
}
