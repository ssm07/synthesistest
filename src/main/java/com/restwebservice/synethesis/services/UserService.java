package com.restwebservice.synethesis.services;

import com.restwebservice.synethesis.beans.User;

import java.util.Optional;

public  interface UserService {
     Optional<User> getUser(Integer id);
     User createUser(User user);
}
