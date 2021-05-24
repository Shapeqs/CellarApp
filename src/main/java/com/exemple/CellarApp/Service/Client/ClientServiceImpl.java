package com.exemple.CellarApp.Service.Client;

import com.exemple.CellarApp.DTO.ClientDTO;
import com.exemple.CellarApp.Model.Client;
import com.exemple.CellarApp.Repository.Client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    /**
     * Le repository client
     */
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        ArrayList<Client> clients = new ArrayList<>();
        for (ClientDTO client : this.clientRepository.findAll()) {
            clients.add(transformDTOtoEntity(client));
        }
        return clients;
    }

    @Override
    public Client findOne(Integer id) {
        return transformDTOtoEntity(clientRepository.findById(id));
    }


    @Override
    public void deleteOne(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }

    @Override
    public void addOne(Client c) {
        clientRepository.addNew(transformEntitytoDTO(c));
    }

    @Override
    public void modifyOne(Integer id, Client c) {
        clientRepository.modify(id, transformEntitytoDTO(c));
    }

    /**
     * Methode transformant un client DTO en entité client
     *
     * @param clientDTO le client à transformer
     * @return l'entité transformée
     */
    private Client transformDTOtoEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setRegistrationDate(clientDTO.getRegistrationDate());
        client.setBirthday(clientDTO.getBirthday());
        client.setTypeClient(clientDTO.getTypeClient());
        client.setEmail(clientDTO.getEmail());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setsiren(clientDTO.getsiren());
        return client;
    }

    /**
     * Methode transformant une entité client en client DTO
     *
     * @param client le client à transformer
     * @return l'entité transformée en DTO
     */
    private ClientDTO transformEntitytoDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setRegistrationDate(client.getRegistrationDate());
        clientDTO.setBirthday(client.getBirthday());
        clientDTO.setTypeClient(client.getTypeClient());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhoneNumber(client.getPhoneNumber());
        clientDTO.setsiren(client.getsiren());
        return clientDTO;
    }
}
