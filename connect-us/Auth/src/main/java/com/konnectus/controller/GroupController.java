package com.konnectus.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.konnectus.domain.Group;
import com.konnectus.service.GroupService;

@RestController
@RequestMapping(value = "/group")
public class GroupController {
	
	private final Logger _log = LoggerFactory.getLogger(getClass());
	@Autowired GroupService groupService;
	
	@RequestMapping(method = RequestMethod.POST)
	public void addGroup(@RequestBody Group group) {
		_log.info("Saving group.");
		groupService.createGroup(group);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Group> getAllGroup() {
		_log.info("Fetching all Groups.");
		return groupService.findAllGroup();
	}
	
	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
	public Group getGroupById(@PathVariable String id) {
		_log.info("Finding group by id");
		return groupService.findGroupById(id);
	}
	
	@RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
	public Group getGroupByName(@PathVariable String name) {
		_log.info("Finding group by name");
		return groupService.findGroupByName(name);
	}
	
	@RequestMapping(value = "/addUserToGroup/{groupId}/{userId}", method = RequestMethod.PUT)
	public void addUserToGroup(@PathVariable String groupId,@PathVariable String userId) {
		_log.info("Adding user to group");
		 groupService.addUserToGroup(groupId,userId);
	}

}
