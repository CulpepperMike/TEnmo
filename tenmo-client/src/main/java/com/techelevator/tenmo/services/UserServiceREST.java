package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserServiceREST implements UserService{
    private final RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;
    private String baseUrl;

    public UserServiceREST(String baseUrl, AuthenticatedUser currentUser) {
        this.currentUser = currentUser;
        this.baseUrl = baseUrl;
    }

    @Override
    public List<User> getAllUsers(AuthenticatedUser authenticatedUser) {
        User[] users = null;
        HttpEntity entity = createEntity();
        try {
            users = restTemplate.exchange(baseUrl + "users/", HttpMethod.GET,
                    entity, User[].class).getBody();
        } catch (RestClientResponseException e) {
            System.out.println(e.getMessage());
        } catch (ResourceAccessException e) {
            System.out.println("Network Error, Please Try Again.");
        }
        List<User> userList = List.of(users);
        return userList;
    }

    @Override
    public User getUserById(int id) {
        User user = new User();
        HttpEntity entity = createEntity();
        try {
            user = restTemplate.exchange(baseUrl + "user/" + id, HttpMethod.GET,
                    entity, User.class).getBody();
        } catch (RestClientResponseException e) {
            System.out.println(e.getMessage());
        } catch (ResourceAccessException e) {
            System.out.println("Network Error, Please Try Again.");
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        HttpEntity entity = createEntity();
        try {
            user = restTemplate.exchange(baseUrl + "user/" + username, HttpMethod.GET,
                    entity, User.class).getBody();
        } catch (RestClientResponseException e) {
            System.out.println(e.getMessage());
        } catch (ResourceAccessException e) {
            System.out.println("Network Error, Please Try Again.");
        }
        return user;
    }

    private HttpEntity<Void> createEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}
