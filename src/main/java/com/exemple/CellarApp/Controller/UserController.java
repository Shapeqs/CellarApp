package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.User;
import com.exemple.CellarApp.Service.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur gérant les requêtes utilisateurs
 */
@RestController
@RequestMapping("/api/employes")
public class UserController {

    /**
     * Le logger de la classe
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Le service utilisateur utilisé pour trouver les bouteilles
     */
    @Autowired
    private UserService userService;

    /**
     * Methode gérant les requêtes Get pour récupérer l'ensemble des utilisateurs
     *
     * @return l'ensemble des utilisateurs
     */
    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYE')")
    public List<User> getAll() {
        return userService.findAll();
    }

    /**
     * Methode gérant les requêtes Get pour récupérer un utilisateur selon son identifiant
     *
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur trouvé ou null sinon
     */
    @GetMapping("/{id}")
    public User getOne(@PathVariable Integer id) {
        return userService.findOneById(id);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer un utilisateur selon son identifiant
     *
     * @param id l'identifiant de l'utilisateur
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteOne(id);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer tous les utilisateurs
     */
    @DeleteMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUsers() {
        userService.deleteAll();
    }

    /**
     * Methode gérant les requêtes Put pour mettre à jour un utilisateur selon son identifiant
     *
     * @param id l'identifiant de l'utilisateur
     * @param e  l'utilisateur
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void modifyUser(@PathVariable Integer id, @RequestBody User e) {
        userService.modifyOne(id, e);
    }

    /**
     * Methode gérant les requêtes Post pour ajouter un utilisateur
     *
     * @param e l'utilisateur
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addUser(@RequestBody User e) {
        userService.addOne(e);
    }
}