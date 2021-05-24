package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Naming;
import com.exemple.CellarApp.Service.Naming.NamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur des requêtes des appelations
 */
@RestController
@RequestMapping("/api/namings")
public class NamingController {

    /**
     * Le service appelation pour trouver les appelations
     */
    @Autowired
    private NamingService namingService;

    /**
     * Methode gérant les requêtes Get pour récupérer l'ensemble des appelations
     *
     * @return l'ensemble des appelations
     */
    @GetMapping
    public List<Naming> getAll() {
        return namingService.findAll();
    }

    /**
     * Methode gérant les requêtes Get pour récupérer une appelation selon son identifiant
     *
     * @param id l'identifiant de l'appelation
     * @return l'appelation trouvée ou null sinon
     */
    @GetMapping("/{id}")
    public Naming getOne(@PathVariable Integer id) {
        return namingService.findOne(id);
    }

    /**
     * Methode gérant les requêtes Get pour récupérer une appelation selon son nom
     *
     * @param name le nom de l'appelation
     * @return l'appelation trouvée ou null sinon
     */
    @GetMapping("/find/{name}")
    public Naming findByName(@PathVariable String name) {
        return namingService.findByName(name);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer une appelation selon son identifiant
     *
     * @param id l'identifiant de l'appelation
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteCastel(@PathVariable Integer id) {
        namingService.deleteOne(id);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer toutes les appelations
     */
    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteCastels() {
        namingService.deleteAll();
    }

    /**
     * Methode gérant les requêtes Put pour mettre à jour une appelation selon son identifiant
     *
     * @param id l'identifiant de l'appelation
     * @param n  l'appelation
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void modifyCastel(@PathVariable Integer id, @RequestBody Naming n) {
        namingService.modifyOne(id, n);
    }

    /**
     * Methode gérant les requêtes Post pour ajouter une appelation
     *
     * @param n l'appelation
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void addCastel(@RequestBody Naming n) {
        namingService.addOne(n);
    }
}
