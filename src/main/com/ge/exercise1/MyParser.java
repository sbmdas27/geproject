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
	User user;

	@Override
	public Application parseApplicationData(String data) {

		char [] arr = data.toCharArray();
		String appId = "";
		String appName = "";
		ArrayList<String> list = new ArrayList<>();
		Set<Character> specials = new HashSet<>(Arrays.asList('(',')','[',']',',',':'));
		StringBuilder sb = new StringBuilder();

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
			}else if("User".equals(list.get(i)) && userList != null){
				userList.add(new GEUser(list.get(i+2),list.get(i+4)));
				i=i+4;
			}else if("Group".equals(list.get(i)) && groupList != null){
				groupList.add(new GEGroup(list.get(i+2),list.get(i+4)));
				i=i+4;
			}
		}
		
		if(!"".equals(appId) && !"".equals(appName) && userList!=null && groupList != null){
			geApplication = new GEApplication(appId, appName, userList, groupList);
		}

		return geApplication;
	}

}
