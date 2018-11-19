package com.deepak.passwdapi.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deepak.passwdapi.AppPropertiesPasswd;
import com.deepak.passwdapi.domain.Group;

@Repository
public class GroupRepository {
	
private AppPropertiesPasswd app;
	
	@Autowired
    public void setApp(AppPropertiesPasswd app) {
        this.app = app;
    }

    
    public List<Group> getAllGroups() {
        Collection<Group> c = app.getGroups();
        List<Group> list = new ArrayList<Group>();
        list.addAll(c);
        return list;
    }

}
