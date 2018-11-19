package com.deepak.passwdapi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.passwdapi.domain.Group;
import com.deepak.passwdapi.repository.GroupRepository;

@Service
public class GroupService {
	
	@Autowired
	GroupRepository groupRepository;
	
	private final Logger log = LoggerFactory.getLogger(GroupService.class);


	public List<Group> findAllGroups() {
		log.info("Getting All groups");
		return groupRepository.getAllGroups();
	}

	public Group findById(long id) {
		List<Group> groupsList = groupRepository.getAllGroups();
		Group empty = new Group();
		for(Group group : groupsList) {
			if(group.getGid()==id) {
				return group;
			}
		}
		return empty;
	}
	
	public List<Group> findAllGroupsByParameters(String name,long gid, List<String> members) {
		// TODO Auto-generated method stub
    	 
    	 	List<Group> allGroups = groupRepository.getAllGroups();
    	 	
    	 	if(allGroups==null || allGroups.isEmpty()) {
    	 		return Collections.EMPTY_LIST;
    	 	}
    	 	List<Group> resultGroups = new ArrayList<>();
    	 	
    	 	for(Group usr : allGroups) {
    	 		if(null!=name && !name.isEmpty() && usr.getName().contains(name)) {
    	 			resultGroups.add(usr);
    	 		}
    	 		if(usr.getGid()==gid) {
    	 			resultGroups.add(usr);
    	 		}
    	 	}
    	 	
    	 	return resultGroups;
	}

}
