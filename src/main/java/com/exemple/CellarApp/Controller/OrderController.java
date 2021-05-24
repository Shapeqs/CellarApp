package com.exemple.CellarApp.Controller;


import com.exemple.CellarApp.Model.Order;
import com.exemple.CellarApp.Service.Order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur des requêtes des commandes
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    /**
     * Le logger de la classe
     */
    private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    /**
     * Le service commande utilisé pour trouver les commandes
     */
    @Autowired
    private OrderService orderService;

    /**
     * Methode gérant les requêtes Get pour récupérer l'ensemble des commandes
     *
     * @return l'ensemble des commandes
     */
    @GetMapping
    public List<Order> getAll() {
        return orderService.findAll();
    }

    /**
     * Methode gérant les requêtes Get pour récupérer une commande selon son identifiant
     *
     * @param id l'identifiant de la commande
     * @return la commande trouvée ou null sinon
     */
    @GetMapping("/{id}")
    public Order getOne(@PathVariable Integer id) {
        return orderService.findOne(id);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer une commande selon son identifiant
     *
     * @param id l'identifiant de la commande
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOne(id);
    }

    /**
     * Methode gérant les requêtes Delete pour supprimer toutes les commandes
     */
    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteOrders() {
        orderService.deleteAll();
    }

    /**
     * Methode gérant les requêtes Put pour mettre à jour une commande selon son identifiant
     *
     * @param id l'identifiant de la commande
     * @param o  la commande
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void modifyOrder(@PathVariable Integer id, @RequestBody Order o) {
        orderService.modifyOne(id, o);
    }

    /**
     * Methode gérant les requêtes Post pour ajouter une commande
     *
     * @param o La commande
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void addOrder(@RequestBody Order o) {
        orderService.addOne(o);
    }
}
