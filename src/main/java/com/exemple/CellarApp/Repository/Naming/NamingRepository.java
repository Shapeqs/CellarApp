package com.exemple.CellarApp.Repository.Naming;

import com.exemple.CellarApp.DTO.NamingDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NamingRepository {

    /**
     * Méthode qui trouve l'ensemble des appelations
     *
     * @return l'ensemble des appelations qui se trouve dans le fichier json
     */
    List<NamingDTO> findAll();

    /**
     * Methode trouve l'appelation depuis son identifiant
     *
     * @param id l'identifiant de l'appelation
     * @return l'appelation trouvé depuis le fichier json ou null sinon
     */
    NamingDTO findById(Integer id);

    /**
     * Methode ajoutant l'appelation en paramètre dans le fichier json
     *
     * @param namingDTO l'appelation à ajouter
     */
    void addNew(NamingDTO namingDTO);

    /**
     * Methode remplaçant l'appelation d'identifiant id avec l'appelation' en paramètre dans le fichier json
     *
     * @param id l'identifiant de l'appelation à modifier
     * @param namingDTO  l'appelation modifié
     */
    void modify(Integer id, NamingDTO namingDTO);

    /**
     * Methode supprimant dans le fichier json l'appelation depuis son identifiant
     *
     * @param id l'identifiant de l'appelation
     */
    void deleteById(Integer id);

    /**
     * Methode supprimant tous les appelations du fichier json
     */
    void deleteAll();

    /**
     * Methode trouve l'appelation depuis son nom
     *
     * @param name le nom de l'appelation
     * @return l'appelation trouvé depuis le fichier json ou null sinon
     */
    NamingDTO findByName(String name);
}
