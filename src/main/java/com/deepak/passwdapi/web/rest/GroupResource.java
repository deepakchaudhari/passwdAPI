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

import com.deepak.passwdapi.domain.Group;
import com.deepak.passwdapi.repository.GroupRepository;
import com.deepak.passwdapi.service.GroupService;

@RestController
public class GroupResource {

	private final Logger log = LoggerFactory.getLogger(GroupResource.class);

	@Autowired
	GroupService groupService;
	
	@Autowired
	private final GroupRepository groupRepository;
	
	public GroupResource(GroupService groupService, GroupRepository groupRepository) {

        this.groupService = groupService;
        this.groupRepository = groupRepository;
    }

	/**
	 * GET /groups : get all groups.
	 *
	 * @return the ResponseEntity with status 200 (OK) and with body all groups
	 */
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public ResponseEntity<List<Group>> listAllGroups() {
		log.info("Getting All groups");
		List<Group> groups = groupService.findAllGroups();
		if (groups.isEmpty()) {
			return new ResponseEntity<List<Group>>(HttpStatus.NO_CONTENT);// You many decide to return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
	}

	@RequestMapping(value = "/groups/{gid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Group> getGroupFormUID(@PathVariable("gid") long gid) {
		log.info("Fetching group with id " + gid);
		Group group = groupService.findById(gid);
		if (group == null) {
			log.info("Group with uid " + gid + " not found");
			return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}


	 @GetMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Group>> getGroupFromDifferentParameters(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "uid", required = false) long uid,
			@RequestParam(value = "gid", required = false) long gid,
			@RequestParam(value = "comment", required = false) String comment,
			@RequestParam(value = "home", required = false) String home,
			@RequestParam(value = "shell", required = false) String shell) {
		
		 log.info("Getting All groups");
			List<Group> groups = groupService.findAllGroups();
			if (groups.isEmpty()) {
				return new ResponseEntity<List<Group>>(HttpStatus.NO_CONTENT);// You many decide to return
																				// HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);	
	}
	 

}

