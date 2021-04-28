package org.abr.audreybr.service;

import javassist.NotFoundException;
import org.abr.audreybr.entity.User;
import org.abr.audreybr.dto.UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> users = new ArrayList<>();

    @Autowired
    private GeolocService geolocService;

    private static int increment = 0;

    public void addUser(String userName) {
        User user = new User(increment++, userName);
        users.add(user);
    }

    public User addUserWithCity(UserRequest userRequest) throws NotFoundException, IllegalArgumentException {
        User user = null;

        if (userRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("The \"name\" field has to be filled.");
        } else {
            user = new User(increment++, userRequest.getName(), geolocService.findCity(userRequest.getLatitude(), userRequest.getLongitude()));
            users.add(user);
        }
        return user;
    }

    public User getUserById(int id) throws NotFoundException {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
    }

    public User getUserByName(String name) throws NotFoundException {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User " + name + " not found"));
    }

    public void setUser(UserRequest userReq) throws NotFoundException {
        User foundUser = getUserById(userReq.getId());
        if (foundUser != null) {
            foundUser.setName(userReq.getName());
            foundUser.setCity(geolocService.findCity(userReq.getLatitude(), userReq.getLongitude()));
        } else {
            throw new NotFoundException("User" + userReq.getId() + " not found");
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void deleteUserById(int id) throws NotFoundException {
        if (!users.remove(getUserById(id))) {
            throw new NotFoundException("User " + id + " not found");
        }
    }
}