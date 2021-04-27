package org.nicolas.nicolasv2.service;

import org.nicolas.nicolasv2.NotFoundException;
import org.nicolas.nicolasv2.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

/*    public User addUser(String name) {
        final User user = new User(name);
        users.add(user);
        return user;
    }*/

    public User addUser(User user) {
        user.setCity(findCity(user.getZipCode()));
        users.add(user);
        return user;
    }

    public long deleteUser(String id) {
        final long found = users.stream().filter(s -> Objects.equals(s.getId(), id)).count();
        users.removeIf(s -> Objects.equals(s.getId(), id));
        return found;
    }

    public User getUser(String id) throws NotFoundException {
        return users.stream()
                .filter(s -> Objects.equals(s.getId(), id))
                .findAny()
                .orElseThrow(() -> new NotFoundException("L'objet n'existe pas"));
    }

    public List<User> getUsers() {
        return users;
    }

    public User updateUser(User user) throws NotFoundException {
        final Optional<User> found = users.stream().filter(s -> Objects.equals(s.getId(), user.getId())).findAny();
        if (found.isEmpty()) {
            throw new NotFoundException("L'objet n'existe pas");
        } else {
            final User foundUser = found.get();
            users.remove(foundUser);
            foundUser.setName(user.getName());
            users.add(foundUser);
            return foundUser;
        }
    }

}


