package com.ge.exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyParser implements Parser{

	Application geApplication;
	List<GEUser> userList;
	List<GEGroup> groupList;
	

	@Override
	public Application parseApplicationData(String data) {

		char [] arr = data.toCharArray();
		String appId = "";
		String appName = "";
		ArrayList<String> list = new ArrayList<>();
		Set<Character> specials = new HashSet<>(Arrays.asList('(',')','[',']',',',':'));
		StringBuilder sb = new StringBuilder();
		int grpCounter = 0;

		boolean afterSpecials = false;

		for(int i = 0; i<arr.length-1 ; i++){

			char curr = arr[i];

			if(Character.isWhitespace(curr) && afterSpecials){
				continue;
			}
			if(specials.contains(curr)){
				afterSpecials = true;
				if(sb.length()>0){
					list.add(sb.toString());
				}
				sb = new StringBuilder();
			}else{
				afterSpecials = false;
				sb.append(""+curr);
			}

		}
		
		for (int i = 0; i<list.size();i++){
			if("Application".equals(list.get(i))){
				appId = list.get(i+2);
				appName = list.get(i+4);
			}else if("users".equals(list.get(i))){
				userList = new ArrayList<>();
			}else if("groups".equals(list.get(i))){
				groupList = new ArrayList<>();
				grpCounter = i;
				break;
			}else if("User".equals(list.get(i)) && userList != null){
				userList.add(new GEUser(list.get(i+2),list.get(i+4)));
				i=i+4;
			}
		}
		
		for(;grpCounter<list.size();grpCounter++){
			if("Group".equals(list.get(grpCounter))){
				groupList.add(new GEGroup(list.get(grpCounter+2), list.get(grpCounter+4)));
				grpCounter = grpCounter+4;
			}else if("User".equals(list.get(grpCounter))){
				GEUser user = new GEUser(list.get(grpCounter+2),list.get(grpCounter+4));
				groupList.get(groupList.size()-1).addUser(user);
				grpCounter = grpCounter+4;
			}
		}
		
		
		if(!"".equals(appId) && !"".equals(appName) && userList!=null && groupList != null){
			geApplication = new GEApplication(appId, appName, userList, groupList);
		}

		return geApplication;
	}

}
