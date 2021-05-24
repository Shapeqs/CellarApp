package com.exemple.CellarApp.Repository.Castel;

import com.exemple.CellarApp.DTO.CastelDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastelRepository {

    /**
     * Méthode qui trouve l'ensemble des chateaux
     *
     * @return l'ensemble des chateaux qui se trouve dans le fichier json
     */
    List<CastelDTO> findAll();

    /**
     * Methode trouve le chateau depuis son identifiant
     *
     * @param id l'identifiant du chateau
     * @return le chateau trouvé depuis le fichier json ou null sinon
     */
    CastelDTO findById(Integer id);

    /**
     * Methode ajoutant le chateau en paramètre dans le fichier json
     *
     * @param castel le chateau à ajouter
     */
    void addNew(CastelDTO castel);

    /**
     * Methode remplaçant le chateau d'identifiant id avec le chateau en paramètre dans le fichier json
     *
     * @param id l'identifiant du chateau à modifier
     * @param castel  le chateau modifié
     */
    void modify(Integer id, CastelDTO castel);

    /**
     * Methode supprimant dans le fichier json le chateau depuis son identifiant
     *
     * @param id l'identifiant du chateau
     */
    void deleteById(Integer id);

    /**
     * Methode supprimant tous les chateaux du fichier json
     */
    void deleteAll();

    /**
     * Methode trouve le chateau depuis son nom
     *
     * @param name le nom du chateau
     * @return le chateau trouvé depuis le fichier json ou null sinon
     */
    CastelDTO findByName(String name);
}
