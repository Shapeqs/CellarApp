package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.User;
import com.exemple.CellarApp.Service.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur des requêtes d'authentification
 */
@RestController
public class AuthController {

    /**
     * Logger de classe
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    /**
     * Le service utilisateur utilisé pour trouver les utilisateurs qui s'authentifie
     */
    @Autowired
    private UserService userService;

    /**
     * Methode gérant le login d'un utilisateur
     *
     * @param user l'utilisateur envoyé
     * @return l'utilisateur si correctement identifié sinon null
     */
    @PostMapping("/api/login")
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
