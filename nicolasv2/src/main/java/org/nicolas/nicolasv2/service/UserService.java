package org.nicolas.nicolasv2.service;

import org.nicolas.nicolasv2.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    List<User> users = new ArrayList<>();

    public  User addUser(String UserName) {
        User user = new User(users.size(), UserName);
        users.add(user);
        return user;
    }

    public  User getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public boolean setOrder(User user) {
        boolean retour = false;

        User userRetour = getUserById(user.getId());
        if (userRetour != null) {
            userRetour.setName(user.getName());
            retour = true;
        }

        return retour;
    }








    public  List<User> getUsers() {
        return users;
    }

    public boolean deleteUserById(int id) {
        return users.remove(getUserById(id));
    }


    public User getUserByName(String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

}


