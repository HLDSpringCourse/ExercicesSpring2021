package org.hld.hugold.service;

import java.util.ArrayList;
import java.util.List;

import org.hld.hugold.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static List<User> liste = new ArrayList<User>() ;
		static {
			liste.add(new User(1, "Jacques",45));
			liste.add(new User(2, "Michel",45));
			liste.add(new User(3, "Norbert",45));
		}
	
	
	public User addUser(User user) {
		liste.add(user);
		return user;
	}
	public User deleteUser(int id) {
		int i;
		User userListe=null;
		for (i = 0; i < liste.size(); i++) {
			userListe=liste.get(i);
			if(id==userListe.getId()){
				break;
			}
		}
		liste.remove(i);
		return userListe;
	}
	public User getUser(int id) {
		User userListe=null;
		for (int i = 0; i < liste.size(); i++) {
			userListe = liste.get(i);
			if(id==userListe.getId()){
				return userListe;
			}
		}
		return null;
	}
	public User updateUser(User user) {
		User userListe=null;
		for (int i = 0; i < liste.size(); i++) {
			 userListe = liste.get(i);
			if(user.getId()==userListe.getId()){
				userListe = user;
			}
		}
		return userListe;
	}
	public List<User> getAll(){
		return liste;
	}
}
