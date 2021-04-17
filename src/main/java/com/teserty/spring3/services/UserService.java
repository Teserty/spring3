package com.teserty.spring3.services;

import com.teserty.spring3.entity.User;

public interface UserService {
    public boolean isUserWithUsernameExist(String username);
    public void createUser(String username, String password);
    public User getCurrentUser();
}
