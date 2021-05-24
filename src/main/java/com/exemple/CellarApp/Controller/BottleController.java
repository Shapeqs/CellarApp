package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Service.Bottle.BottleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur des requêtes des bouteilles
 */
@RestController
@RequestMapping("/api/bottles")
public class BottleController {

    /**
     * Le logger de la classe
     */
    private final Logger LOGGER = LoggerFactory.getLogger(BottleController.class);

    /**
     * Le service bouteille utilisé pour trouver les bouteilles
     */
    @Autowired
    private BottleService bottleService;

    /**
     * Methode gérant les requêtes Get pour récupérer l'ensemble des bouteilles
     *
     * @return l'ensemble des bouteilles
     */
    @GetMapping
    public List<Bottle> getAll() {
        return bottleService.findAll();
    }

    /**
     * Methode gérant les requêtes Get pour récupérer une bouteille selon son identifiant
     *
     * @param id l'identifiant de la bouteille
     * @return la bouteille trouvée ou null sinon
     */
    @GetMapping("/{id}")
    public Bottle getOne(@PathVariable Integer id) {
        return bottleService.findOne(id);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer une bouteille selon son identifiant
     *
     * @param id l'identifiant de la bouteille
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteBottle(@PathVariable Integer id) {
        bottleService.deleteOne(id);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer toutes les bouteilles
     */
    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteBottles() {
        bottleService.deleteAll();
    }

    /**
     * Methode gérant les requêtes Put pour mettre à jour une bouteille selon son identifiant
     *
     * @param id l'identifiant de la bouteille
     * @param b  la bouteille
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void modifyBottle(@PathVariable Integer id, @RequestBody Bottle b) {
        bottleService.modifyOne(id, b);
    }

    /**
     * Methode gérant les requêtes Post pour ajouter une bouteille
     *
     * @param b La bouteille
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void addBottle(@RequestBody Bottle b) {
        bottleService.addOne(b);
    }

}
