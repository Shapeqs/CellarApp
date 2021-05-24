package com.exemple.CellarApp.Service.Order;


import com.exemple.CellarApp.Model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    /**
     * Méthode qui trouve l'ensemble des commandes
     * @return l'ensemble des entités Order qui se trouve dans le repository
     */
    List<Order> findAll();

    /**
     * Methode trouve la commande depuis son identifiant
     * @param id l'identifiant de la commande
     * @return la commande trouvé depuis le repository ou null sinon
     */
    Order findOne(Integer id);

    /**
     * Methode supprimant dans le repository la commande depuis son identifiant
     * @param id l'identifiant de la commande
     */
    void deleteOne(Integer id);

    /**
     * Methode supprimant tous les commandes du repository
     */
    void deleteAll();

    /**
     * Methode ajoutant la commande en paramètre dans le repository
     * @param o la commande à ajouter
     */
    void addOne(Order o);

    /**
     * Methode remplaçant la commande d'identifiant id avec la commande en paramètre dans le repository
     * @param id l'identifiant de la commande à modifier
     * @param o la commande modifié
     */
    void modifyOne(Integer id, Order o);
}
