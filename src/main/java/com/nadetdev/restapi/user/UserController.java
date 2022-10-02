package com.nadetdev.restapi.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/all")
    List<User> findAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    User findOneUser(@PathVariable("id") int userId) {
        return repository.findById(userId).orElse(null);
    }

    @PostMapping("/add")
    User addNewUser(@RequestBody User newUser) {
       return repository.save(newUser);
    }

    @PutMapping("/update/{id}")
    User updateUser(@PathVariable("id") int existUserId, @RequestBody User existUser) {

        User oldUser = repository.findById(existUserId).orElse(null);
        oldUser.setName(existUser.getName());
        oldUser.setEmail(existUser.getEmail());
        oldUser.setPassword(existUser.getPassword());
        
       return repository.save(oldUser);
    }

    @GetMapping("/delete/{id}")
    Integer deleteUser(@PathVariable("id") int userId) {
        repository.deleteById(userId); 
        return userId;
    }
 
}
