package com.deepak.passwdapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.passwdapi.domain.User;
import com.deepak.passwdapi.repository.UserRepository;

/**
 * Service class for managing users.
 */
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserService.class);


    public List<User> findAllUsers() {
		// TODO Auto-generated method stub
    	 	log.info("Getting All users");
		return userRepository.getAllUsers();
	}


	public User findById(long id) {
		List<User> userList = userRepository.getAllUsers();
		User empty = new User();
		for(User usr : userList) {
			if(usr.getUid()==id) {
				return usr;
			}
		}
		return empty;
	}
	
	
	public List<User> findAllUsersByParameters(String name,long uid,long gid, String comment,String home,String shell) {
		// TODO Auto-generated method stub
    	 	log.info("Getting All users");
    	 	List<User> allUsers = userRepository.getAllUsers();
    	 	
    	 	if(allUsers==null || allUsers.isEmpty()) {
    	 		return Collections.EMPTY_LIST;
    	 	}
    	 	List<User> resultUsers = new ArrayList<>();
    	 	
    	 	for(User usr : allUsers) {
    	 		if(null!=name && !name.isEmpty() && usr.getName().contains(name)) {
    	 			resultUsers.add(usr);
    	 		}
    	 		if(usr.getUid()==uid) {
    	 			resultUsers.add(usr);
    	 		}
    	 		if(usr.getGid()==gid) {
    	 			resultUsers.add(usr);
    	 		}
    	 		if(null!=home && !home.isEmpty() && usr.getHome().contains(home)) {
    	 			resultUsers.add(usr);
    	 		}
    	 		if(null!=shell && !shell.isEmpty() && usr.getShell().contains(shell)) {
    	 			resultUsers.add(usr);
    	 		}
    	 		if(null!=comment && !comment.isEmpty() && usr.getComment().contains(comment)) {
    	 			resultUsers.add(usr);
    	 		}
    	 	}
    	 	
    	 	return resultUsers;
	}
    

    

   
}
