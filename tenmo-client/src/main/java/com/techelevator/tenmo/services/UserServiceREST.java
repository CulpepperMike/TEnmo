package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;

import java.util.List;

public class UserServiceREST implements UserService{
    @Override
    public List<User> getAllUsers(AuthenticatedUser authenticatedUser) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}
