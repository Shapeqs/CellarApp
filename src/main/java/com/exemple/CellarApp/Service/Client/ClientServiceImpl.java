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

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        ArrayList<Client> clients = new ArrayList<>();
        for (ClientDTO client : this.clientRepository.findAll()) {
            clients.add(transformDAOtoEntity(client));
        }
        return clients;
    }

    @Override
    public Client findOne(Integer id) {
        return transformDAOtoEntity(clientRepository.findById(id));
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

    private Client transformDAOtoEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setRegistrationDate(clientDTO.getRegistrationDate());
        client.setBirthday(clientDTO.getBirthday());
        return client;
    }

    private ClientDTO transformEntitytoDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setRegistrationDate(client.getRegistrationDate());
        clientDTO.setBirthday(client.getBirthday());
        return clientDTO;
    }
}
