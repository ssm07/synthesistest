package com.restwebservice.synethesis.controllers;

import com.restwebservice.synethesis.beans.User;
import com.restwebservice.synethesis.exception.UserNotFoundException;
import com.restwebservice.synethesis.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 *
 * <p> This class provides an API for creating and fetching user details.</p>
 *
 * @Author Saurabh Moghe
 * */
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping(path = "/user/{id}")
    public  @ResponseBody User getUser(@PathVariable Integer id){
        log.info(" id is"+id);
        Optional<User> user= userService.getUser(id);
           if(!user.isPresent())
               throw  new UserNotFoundException("user id "+id +" does not exist");
        return  user.get();
    }


    @PostMapping(path = "/user")
    public  @ResponseBody ResponseEntity<Object> createUser( @Valid @RequestBody  User user){
        log.info("UserController :: creatUser method");
        log.info(" Name "+user.getName());
        userService.createUser(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path="/hello")
    public String hello(){
        return "hello";
    }


}
