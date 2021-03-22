package com.teserty.spring3.services;

import com.teserty.spring3.enities.User;

public interface UserService {
    public boolean isUserWithUsernameExist(String username);
    public void createUser(String username, String password);
    public User getCurrentUser();
}
