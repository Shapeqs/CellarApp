package com.exemple.CellarApp.Service.Castel;


import com.exemple.CellarApp.Model.Castel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CastelService {

    /**
     * Méthode qui trouve l'ensemble des chateaux
     * @return l'ensemble des entités Castel qui se trouve dans le repository
     */
    List<Castel> findAll();

    /**
     * Methode trouve le chateau depuis son identifiant
     * @param id l'identifiant du chateau
     * @return le chateau trouvé depuis le repository ou null sinon
     */
    Castel findOne(Integer id);

    /**
     * Methode trouve le chateau depuis son nom
     * @param name le nom du chateau
     * @return le chateau trouvé depuis le repository ou null sinon
     */
    Castel findByName(String name);

    /**
     * Methode supprimant dans le repository le chateau depuis son identifiant
     * @param id l'identifiant du chateau
     */
    void deleteOne(Integer id);

    /**
     * Methode supprimant tous les chateaux du repository
     */
    void deleteAll();

    /**
     * Methode ajoutant le chateau en paramètre dans le repository
     * @param c le chateau à ajouter
     */
    void addOne(Castel c);

    /**
     * Methode remplaçant le chateau d'identifiant id avec le chateau en paramètre dans le repository
     * @param id l'identifiant du chateau à modifier
     * @param c le chateau modifié
     */
    void modifyOne(Integer id, Castel c);

}
