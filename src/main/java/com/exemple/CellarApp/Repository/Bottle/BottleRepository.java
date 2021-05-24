package com.exemple.CellarApp.Repository.Bottle;

import com.exemple.CellarApp.DTO.BottleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BottleRepository {

    /**
     * Methode retournant l'ensemble des bouteilles dans le fichier json
     *
     * @return toutes les bouteilles
     */
    List<BottleDTO> findAll();

    /**
     * Methode trouve la bouteille depuis son identifiant dans le fichier json
     *
     * @param id l'identifiant de la bouteille
     * @return la bouteille trouvé depuis le fichier json ou null sinon
     */
    BottleDTO findById(Integer id);

    /**
     * Methode ajoutant la bouteille en paramètre dans le fichier json
     *
     * @param bottle la bouteille à ajouter
     */
    void addNew(BottleDTO bottle);

    /**
     * Methode remplaçant la bouteille d'identifiant id avec la bouteille en paramètre dans le repository
     *
     * @param id l'identifiant de la bouteille à modifier
     * @param bottle  la bouteille modifié
     */
    void modify(Integer id, BottleDTO bottle);

    /**
     * Methode supprimant dans le fichier json la bouteille depuis son identifiant
     *
     * @param id l'identifiant de la bouteille
     */
    void deleteById(Integer id);

    /**
     * Methode supprimant tous les bouteilles du fichier json
     */
    void deleteAll();

}
