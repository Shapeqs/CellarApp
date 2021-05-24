package com.exemple.CellarApp.Service.Bottle;


import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Model.Bottle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BottleService {

    /**
     * Méthode qui trouve l'ensemble des bouteilles
     *
     * @return l'ensemble des entités Bottle qui se trouve dans le repository
     */
    List<Bottle> findAll();

    /**
     * Methode trouve la bouteille depuis son identifiant
     *
     * @param id l'identifiant de la bouteille
     * @return la bouteille trouvé depuis le repository ou null sinon
     */
    Bottle findOne(Integer id);

    /**
     * Methode supprimant dans le repository la bouteille depuis son identifiant
     *
     * @param id l'identifiant de la bouteille
     */
    void deleteOne(Integer id);

    /**
     * Methode supprimant tous les bouteilles du repository
     */
    void deleteAll();

    /**
     * Methode ajoutant la bouteille en paramètre dans le repository
     *
     * @param b la bouteille à ajouter
     */
    void addOne(Bottle b);

    /**
     * Methode remplaçant la bouteille d'identifiant id avec la bouteille en paramètre dans le repository
     *
     * @param id l'identifiant de la bouteille à modifier
     * @param b  la bouteille modifié
     */
    void modifyOne(Integer id, Bottle b);

}
