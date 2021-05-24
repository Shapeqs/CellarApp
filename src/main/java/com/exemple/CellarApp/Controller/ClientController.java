package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Client;
import com.exemple.CellarApp.Service.Client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur des requêtes des clients
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    /**
     * Le service client utilisé pour trouver les clients
     */
    @Autowired
    private ClientService clientService;

    /**
     * Methode gérant les requêtes Get pour récupérer l'ensemble des clients
     *
     * @return l'ensemble des clients
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public List<Client> getAll() {
        return clientService.findAll();
    }

    /**
     * Methode gérant les requêtes Get pour récupérer un client selon son identifiant
     *
     * @param id l'identifiant du client
     * @return le client trouvé ou null sinon
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public Client getOne(@PathVariable Integer id) {
        return clientService.findOne(id);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer un client selon son identifiant
     *
     * @param id l'identifiant du client
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteBottle(@PathVariable Integer id) {
        clientService.deleteOne(id);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer tous les clients
     */
    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteBottles() {
        clientService.deleteAll();
    }

    /**
     * Methode gérant les requêtes Put pour mettre à jour un client selon son identifiant
     *
     * @param id l'identifiant du client
     * @param c  le client
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void modifyBottle(@PathVariable Integer id, @RequestBody Client c) {
        clientService.modifyOne(id, c);
    }

    /**
     * Methode gérant les requêtes Post pour ajouter un client
     *
     * @param c le client
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void addBottle(@RequestBody Client c) {
        clientService.addOne(c);
    }


}
