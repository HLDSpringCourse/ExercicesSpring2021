package org.nicolas.nicolasv2.service;

import org.nicolas.nicolasv2.NotFoundException;
import org.nicolas.nicolasv2.dto.FoundCity;
import org.nicolas.nicolasv2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    @Autowired
    private RestTemplate restTemplate;

/*    public User addUser(String name) {
        final User user = new User(name);
        users.add(user);
        return user;
    }*/

    public User addUser(User user) {

        if(users.size() > 0) {
            user.setId(users.get(users.size() - 1).getId() + 1);
        } else {
            user.setId(1);
        }

        user.setCity(findCity(user.getZipCode()));
        user.setDepartmentCode((findDept(user.getZipCode())));
        users.add(user);
        return user;
    }


    public long deleteUser(int id) {
        final long found = users.stream().filter(user -> user.getId() == id).count();
        users.removeIf( user -> user.getId() == id);
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

/*    public User updateUser(User user) throws NotFoundException {
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
    }*/

    private String findCity(String zipCode) {
        return restTemplate.exchange(
                "https://geo.api.gouv.fr/communes?codePostal=" + zipCode + "&fields=nom,codeDepartement&format=json&geometry=centre",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<FoundCity>>() {
                })
                .getBody()
                .stream()
                .findFirst()
                .map(FoundCity::getNom)
                //.map(FoundCity::getDepartmentCode)
                .orElseThrow(() -> new NotFoundException(
                        "code " + zipCode + " non valide"));
    }

    private String findDept(String zipCode) {
        return restTemplate.exchange(
                "https://geo.api.gouv.fr/communes?codePostal=" + zipCode + "&fields=nom,codeDepartement&format=json&geometry=centre",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<FoundCity>>() {
                })
                .getBody()
                .stream()
                .findFirst()
                //.map(FoundCity::getNom)
                .map(FoundCity::getCodeDepartement)
                .orElseThrow(() -> new NotFoundException(
                        "code " + zipCode + " non valide"));
    }


    public User updateUser(User user) throws NotFoundException {
            return users.stream().filter(s -> Objects.equals(s.getId(), user.getId())).findAny().map(foundUser -> {
                users.remove(foundUser);
                foundUser.setName(user.getName());
                foundUser.setZipCode(user.getZipCode());
                foundUser.setCity(findCity(user.getZipCode()));
                foundUser.setDepartmentCode(findDept(user.getDepartmentCode()));
                users.add(foundUser);
                return foundUser;
        }).orElseThrow(() -> new NotFoundException("L'objet n'existe pas"));
    }


}


