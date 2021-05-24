package com.exemple.CellarApp.Repository.User;

import com.exemple.CellarApp.DTO.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    /**
     * Méthode qui trouve l'ensemble des utilisateurs
     *
     * @return l'ensemble des utilisateurs qui se trouve dans le fichier json
     */
    List<UserDTO> findAll();

    /**
     * Methode trouve l'utilisateur depuis son identifiant
     *
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur trouvé depuis le fichier json ou null sinon
     */
    UserDTO findById(Integer id);

    /**
     * Methode trouve l'utilisateur depuis son nom d'utilisateur
     *
     * @param username le nom d'utilisateur
     * @return l'utilisateur trouvé depuis le fichier json ou null sinon
     */
    UserDTO findByUsername(String username);

    /**
     * Methode ajoutant l'utilisateur en paramètre dans le fichier json
     *
     * @param employe l'utilisateur à ajouter
     */
    void addNew(UserDTO employe);

    /**
     * Methode remplaçant l'utilisateur d'identifiant id avec l'utilisateur en paramètre dans le fichier json
     *
     * @param id      l'identifiant de l'utilisateur à modifier
     * @param employe l'utlisateur modifié
     */
    void modify(Integer id, UserDTO employe);

    /**
     * Methode supprimant dans le fichier json l'utilisateur depuis son identifiant
     *
     * @param id l'identifiant de l'utilisateur
     */
    void deleteById(Integer id);

    /**
     * Methode supprimant tous les utilisateurs du fichier json
     */
    void deleteAll();
}
