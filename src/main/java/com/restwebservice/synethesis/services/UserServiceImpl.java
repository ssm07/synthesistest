package com.restwebservice.synethesis.services;

import com.restwebservice.synethesis.beans.User;
import com.restwebservice.synethesis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
       return userRepository.save(user);
    }
}
