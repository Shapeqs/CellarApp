package com.exemple.CellarApp.Service.Client;

import com.exemple.CellarApp.Model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    /**
     * Méthode qui trouve l'ensemble des clients
     * @return l'ensemble des entités Client qui se trouve dans le repository
     */
    List<Client> findAll();

    /**
     * Methode trouve le client depuis son identifiant
     * @param id l'identifiant du client
     * @return le client trouvé depuis le repository ou null sinon
     */
    Client findOne(Integer id);

    /**
     * Methode supprimant dans le repository le client depuis son identifiant
     * @param id l'identifiant du client
     */
    void deleteOne(Integer id);

    /**
     * Methode supprimant tous les clients du repository
     */
    void deleteAll();

    /**
     * Methode ajoutant le client en paramètre dans le repository
     * @param c le client à ajouter
     */
    void addOne(Client c);

    /**
     * Methode remplaçant le client d'identifiant id avec le client en paramètre dans le repository
     * @param id l'identifiant du client à modifier
     * @param c le client modifié
     */
    void modifyOne(Integer id, Client c);
}
