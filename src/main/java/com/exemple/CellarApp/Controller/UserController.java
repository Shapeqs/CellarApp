package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.User;
import com.exemple.CellarApp.Service.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYE')")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Integer id) {
        return userService.findOneById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteOne(id);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUsers() {
        userService.deleteAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void modifyUser(@PathVariable Integer id, @RequestBody User e) {
        userService.modifyOne(id, e);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addUser(@RequestBody User e) {
        userService.addOne(e);
    }
}