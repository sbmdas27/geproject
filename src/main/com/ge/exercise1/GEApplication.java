package com.ge.exercise1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GEApplication extends Application{

	Map<String, GEUser> userMap;
	Map<String, GEGroup> groupMap;


	public GEApplication(String appId, String appName, List<GEUser> userList, List<GEGroup> groupList) {
		super(appId, appName);
		this.userMap = new HashMap<>();
		this.groupMap = new HashMap<>();
		populateUserMap(userList);
		populateGroupMap(groupList);
	}
	

	private void populateUserMap(List<GEUser> userList) {
		userList.forEach(user -> {
			userMap.put(user.getId(), user);
		});
	}

	private void populateGroupMap(List<GEGroup> groupList) {
		groupList.forEach(group -> {
			groupMap.put(group.getId(), group);
		});
	}

	@Override
	public Collection<User> getUsers() {
		ArrayList<User> userCollection = new ArrayList<>();
		for(Map.Entry<String, GEUser> entry :userMap.entrySet()){
			userCollection.add((User)entry.getValue());
		}
		return userCollection;
	}

	@Override
	public User getUser(String userId) {
		return userMap.get(userId);
	}

	@Override
	public Collection<Group> getGroups() {
		ArrayList<Group> groupCollection = new ArrayList<>();
		for(Map.Entry<String, GEGroup> entry :groupMap.entrySet()){
			groupCollection.add((Group)entry.getValue());
		}
		return groupCollection;
	}

	@Override
	public Group getGroup(String groupId) {
		return groupMap.get(groupId);
	}

}
