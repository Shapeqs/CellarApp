package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Castel;
import com.exemple.CellarApp.Service.Castel.CastelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur des requêtes des chateaux
 */
@RestController
@RequestMapping("/api/castels")
public class CastelController {

    /**
     * Le service chateau utilisé pour trouver les chateaux
     */
    @Autowired
    private CastelService castelService;

    /**
     * Methode gérant les requêtes Get pour récupérer l'ensemble des chateaux
     *
     * @return l'ensemble des chateaux
     */
    @GetMapping
    public List<Castel> getAll() {
        return castelService.findAll();
    }

    /**
     * Methode gérant les requêtes Get pour récupérer un chateau selon son identifiant
     *
     * @param id l'identifiant du chateau
     * @return un chateau trouvée ou null sinon
     */
    @GetMapping("{id}")
    public Castel getOne(@PathVariable Integer id) {
        return castelService.findOne(id);
    }

    /**
     * Methode gérant les requêtes Get pour récupérer un chateau selon son nom
     *
     * @param name le nom du chateau
     * @return un chateau trouvée ou null sinon
     */
    @GetMapping("/find/{name}")
    public Castel findByName(@PathVariable String name) {
        return castelService.findByName(name);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer un chateau selon son identifiant
     *
     * @param id l'identifiant du chateau
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteCastel(@PathVariable Integer id) {
        castelService.deleteOne(id);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer tous les chateaux
     */
    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteCastels() {
        castelService.deleteAll();
    }

    /**
     * Methode gérant les requêtes Put pour mettre à jour un chateau selon son identifiant
     *
     * @param id l'identifiant du chateau
     * @param c  le chateau
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void modifyCastel(@PathVariable Integer id, @RequestBody Castel c) {
        castelService.modifyOne(id, c);
    }

    /**
     * Methode gérant les requêtes Post pour ajouter un chateau
     *
     * @param c Le chateau
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void addCastel(@RequestBody Castel c) {
        castelService.addOne(c);
    }

}
