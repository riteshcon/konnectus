package com.konnectus.service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.konnectus.domain.Group;
import com.konnectus.domain.User;
import com.konnectus.repository.GroupRepository;

@Service
public class GroupService {

	private final Logger _log = LoggerFactory.getLogger(getClass());
	@Autowired GroupRepository groupRepository;
	@Autowired UserService userService;
	
	public void createGroup(Group group) {
		_log.info("in service saving group");
		groupRepository.save(group);
	}
	
	public List<Group> findAllGroup() {
		return groupRepository.findAll();
	}
	
	public Group findGroupById(String id) {
		return groupRepository.findById(id).get();
	}
	
	public Group findGroupByName(String name) {
		ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name", contains().ignoreCase());
				

		Group searchGroup = new Group();
		searchGroup.setName(name);

		Example<Group> searchExample = Example.of(searchGroup, matcher);
		return groupRepository.findOne(searchExample).get();
	}
	
	
	public void addUserToGroup(String groupId, String userId) {
				
		Group toUpdate = groupRepository.findById(groupId).get();
		
		User member = new User(userId);
		toUpdate.getGroupMembers().add(member);
		
		//toUpdate.getGroupMembers().add(userService.findUserById(userId));
		groupRepository.save(toUpdate);
		
	}
	
	
}
