package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.repositories.UserRepository;

public class LoginController {
    private UserRepository userRepository;
    public LoginController() {
        this.userRepository = new UserRepository();
    }
    public User login(String username, String password) {

        return  userRepository.findOneUserByUsernameAndPassword(username, password);
    }
}
