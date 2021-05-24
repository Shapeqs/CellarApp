package com.exemple.CellarApp.Repository.Client;

import com.exemple.CellarApp.DTO.ClientDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository {
    /**
     * Méthode qui trouve l'ensemble des clients
     *
     * @return l'ensemble des clients qui se trouve dans le fichier json
     */
    List<ClientDTO> findAll();

    /**
     * Methode trouve le client depuis son identifiant
     *
     * @param id l'identifiant du client
     * @return le client trouvé depuis le fichier json ou null sinon
     */
    ClientDTO findById(Integer id);

    /**
     * Methode ajoutant le client en paramètre dans le fichier json
     *
     * @param client le client à ajouter
     */
    void addNew(ClientDTO client);

    /**
     * Methode remplaçant le client d'identifiant id avec le client en paramètre dans le fichier json
     *
     * @param id     l'identifiant du client à modifier
     * @param client le client modifié
     */
    void modify(Integer id, ClientDTO client);

    /**
     * Methode supprimant dans le fichier json le client depuis son identifiant
     *
     * @param id l'identifiant du client
     */
    void deleteById(Integer id);

    /**
     * Methode supprimant tous les clients du fichier json
     */
    void deleteAll();
}
