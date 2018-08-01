package com.konnectus.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konnectus.domain.Group;
import com.konnectus.repository.GroupRepository;

@Service
public class GroupService {

	private final Logger _log = LoggerFactory.getLogger(getClass());
	@Autowired GroupRepository groupRepository;
	
	public void createGroup(Group group) {
		groupRepository.save(group);
	}
	
	public List<Group> findAllGroup() {
		return groupRepository.findAll();
	}
	
	public Group findGroupByName(String groupName) {
		return groupRepository.findById(groupName).get();
	}
	
	
}
