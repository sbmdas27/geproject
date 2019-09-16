package com.ge.exercise1;

import java.util.ArrayList;

public class GEGroup extends Group{

	ArrayList<GEUser> userList;
	public GEGroup(String id, String name) {
		super(id, name);
		this.userList = new ArrayList<>();
	}
	
	public void addUser(GEUser user){
		userList.add(user);
		size++;
	}
	
	public void removeUser(GEUser user){
		userList.remove(user);
		size--;
	}

}
