package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.User;
import com.exemple.CellarApp.Service.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/api/login")
    public User login(@RequestBody User user) {
        User oneByLogin = userService.findOneByLogin(user.getUsername());
        if (null != oneByLogin && user.getPassword().equals(oneByLogin.getPassword())) {
            return oneByLogin;
        } else {
            LOGGER.error(String.format("Username %s not found ! ", user.getUsername()));
            return null;
        }
    }
}
