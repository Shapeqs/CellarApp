package com.exemple.CellarApp.Service.Naming;


import com.exemple.CellarApp.Model.Naming;
import com.exemple.CellarApp.Model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NamingService {

    /**
     * Méthode qui trouve l'ensemble des appelations
     * @return l'ensemble des entités Naming qui se trouve dans le repository
     */
    List<Naming> findAll();

    /**
     * Methode trouve l'appelation depuis son identifiant
     * @param id l'identifiant de l'appelation
     * @return l'appelation trouvé depuis le repository ou null sinon
     */
    Naming findOne(Integer id);

    /**
     * Methode trouve l'appelation depuis son nom
     * @param name le nom de l'appelation
     * @return l'appelation trouvé depuis le repository ou null sinon
     */
    Naming findByName(String name);

    /**
     * Methode supprimant dans le repository l'appelation depuis son identifiant
     * @param id l'identifiant de l'appelation
     */
    void deleteOne(Integer id);

    /**
     * Methode supprimant tous les appelations du repository
     */
    void deleteAll();

    /**
     * Methode ajoutant l'appelation en paramètre dans le repository
     * @param n l'appelation à ajouter
     */
    void addOne(Naming n);

    /**
     * Methode remplaçant l'appelation d'identifiant id avec l'appelation en paramètre dans le repository
     * @param id l'identifiant de l'appelation à modifier
     * @param c l'appelation modifié
     */
    void modifyOne(Integer id, Naming c);
}
