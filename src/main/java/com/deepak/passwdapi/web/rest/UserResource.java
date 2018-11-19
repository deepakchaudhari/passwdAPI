package com.deepak.passwdapi.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.passwdapi.domain.User;
import com.deepak.passwdapi.repository.UserRepository;
import com.deepak.passwdapi.service.UserService;


@RestController
public class UserResource {

	private final Logger log = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	UserService userService;
	
    private final UserRepository userRepository;
	
	public UserResource(UserService userService, UserRepository userRepository) {

        this.userService = userService;
        this.userRepository = userRepository;
    }

	/**
	 * GET /users : get all users.
	 *
	 * @return the ResponseEntity with status 200 (OK) and with body all users
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		log.info("Getting All users");
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You many decide to return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserFormUID(@PathVariable("uid") long uid) {
		log.info("Fetching User with id " + uid);
		User user = userService.findById(uid);
		if (user == null) {
			log.info("User with uid " + uid + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}


	@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getUserFromDifferentParameters(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "uid", required = false) long uid,
			@RequestParam(value = "gid", required = false) long gid,
			@RequestParam(value = "comment", required = false) String comment,
			@RequestParam(value = "home", required = false) String home,
			@RequestParam(value = "shell", required = false) String shell) {
		
			List<User> users = userService.findAllUsersByParameters(name, uid, gid, comment, home, shell);
			if (users.isEmpty()) {
				return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You many decide to return
																				// HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);	
	}
	 
	 @RequestMapping(value = "/users/{uid}/groups",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<User>> getGroupsFromUID(@PathVariable("uid") String uid ) {
			
				List<User> users = userService.findAllUsers();
				if (users.isEmpty()) {
					return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You many decide to return
																					// HttpStatus.NOT_FOUND
				}
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);	
		}

}
