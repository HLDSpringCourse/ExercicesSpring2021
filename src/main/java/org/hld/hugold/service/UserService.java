package org.hld.hugold.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hld.hugold.dao.UserRepository;
import org.hld.hugold.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private GeoApi service;
	@Autowired
	private UserRepository userRepo;
	
	private  List<User> liste = new ArrayList<User>();


	public User addUser(User user) {
		user.setDepartement(service.findDepartement(user.getDepartementCode()));
		userRepo.save(user);
	//	liste.add(user);
		return user;
	}
	
	public User deleteUser(int id) {
	/*	int i;
		User userListe=null;
		for (i = 0; i < liste.size(); i++) {
			userListe=liste.get(i);
			if(id==userListe.getId()){
				break;
			}
		}
		liste.remove(i);
		*/
		User user = userRepo.findById(id).get();
		userRepo.deleteById(id);
		return user;
	}
	
	public Optional<User> getUser(int id) {
	/*	User userListe=null;
		for (int i = 0; i < liste.size(); i++) {
			userListe = liste.get(i);
			if(id==userListe.getId()){
				return userListe;
			}
		} */
		return userRepo.findById(id);
	}
	public User updateUser(User user) {
		int id = user.getId();
		for (int i = 0; i < liste.size(); i++) {			
			if( id ==liste.get(i).getId()){
				user.setDepartement(service.findDepartement(user.getDepartementCode()));
				liste.set(i, user);
			}
		}
		return user;
	}
	public List<User> getAll(){
		return userRepo.findAll();
	}
}
