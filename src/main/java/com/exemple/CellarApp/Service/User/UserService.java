package com.exemple.CellarApp.Service.User;

import com.exemple.CellarApp.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     * Méthode qui trouve l'ensemble des utilisateurs
     * @return l'ensemble des entités User qui se trouve dans le repository
     */
    List<User> findAll();

    /**
     * Methode trouve l'utilisateur depuis son identifiant
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur trouvé depuis le repository ou null sinon
     */
    User findOneById(Integer id);

    /**
     * Methode trouve l'utilisateur depuis son nom d'utilisateur
     * @param username nom d'utilisateur de l'utilisateur
     * @return l'utilisateur trouvé depuis le repository ou null sinon
     */
    User findOneByLogin(String username);

    /**
     * Methode supprimant dans le repository l'utilsateur depuis son identifiant
     * @param id l'identifiant de l'utilisateur
     */
    void deleteOne(Integer id);

    /**
     * Methode supprimant tous les utilisateurs du repository
     */
    void deleteAll();

    /**
     * Methode ajoutant l'utilisateur en paramètre dans le repository
     * @param e l'utilisateur à ajouter
     */
    void addOne(User e);

    /**
     * Methode remplaçant l'utilisateur d'identifiant id avec l'utilisateur en paramètre dans le repository
     * @param id l'identifiant de l'utilisateur à modifier
     * @param e l'utilisateur modifié
     */
    void modifyOne(Integer id, User e);
}
