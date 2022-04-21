package com.faisalabdulle.casestudy.service;

import com.faisalabdulle.casestudy.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
