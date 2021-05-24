package com.exemple.CellarApp.Repository.Order;


import com.exemple.CellarApp.DTO.OrderDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
    /**
     * Méthode qui trouve l'ensemble des commandes
     *
     * @return l'ensemble des commandes qui se trouve dans le fichier json
     */
    List<OrderDTO> findAll();

    /**
     * Methode trouve la commade depuis son identifiant
     *
     * @param id l'identifiant de la commande
     * @return la commande trouvé depuis le fichier json ou null sinon
     */
    OrderDTO findById(Integer id);

    /**
     * Methode ajoutant la commande en paramètre dans le fichier json
     *
     * @param orderDTO la commande à ajouter
     */
    void addNew(OrderDTO orderDTO);

    /**
     * Methode remplaçant la commande d'identifiant id avec la commande en paramètre dans le fichier json
     *
     * @param id l'identifiant de la commande à modifier
     * @param orderDTO  la commande modifié
     */
    void modify(Integer id, OrderDTO orderDTO);

    /**
     * Methode supprimant dans le fichier json la commande depuis son identifiant
     *
     * @param id l'identifiant de la commande
     */
    void deleteById(Integer id);

    /**
     * Methode supprimant toutes les commandes du fichier json
     */
    void deleteAll();
}
