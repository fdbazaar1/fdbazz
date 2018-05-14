package com.in28minutes.rest.webservices.restfulwebservices.bean.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> userList = new ArrayList<>();
	private static int useCnt = 4;

	public UserDaoService() {
	}

	static {
		userList.add(new User(1, "Adam", new Date()));
		userList.add(new User(2, "Eve", new Date()));
		userList.add(new User(3, "Jack", new Date()));
		userList.add(new User(4, "Pandit", new Date()));
	}

	public List<User> findAll() {
		return userList;
	}

	public User save(User u) {
		if (u.getId() == null) {
			useCnt++;
			u.setId(useCnt);
		}
		userList.add(u);
		return u;
	}

	public User findOne(int userId) {
		User u1 = null;
		for (User user : userList) {
			if (user.getId().intValue() == userId) {
				u1 = user;
				break;
			}
		}
		return u1;
	}

	public User deleteOne(int userId) {
		Iterator<User> iterator = userList.iterator();		
		while(iterator.hasNext()){
			User user  =  iterator.next();
			if(user.getId() == userId){
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
