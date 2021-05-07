package com.exemple.CellarApp.Service.Client;

import com.exemple.CellarApp.DTO.BottleDTO;
import com.exemple.CellarApp.DTO.ClientDTO;
import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Model.Client;
import com.exemple.CellarApp.Repository.Client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

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

    private Client transformDAOtoEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setRegistrationDate(clientDTO.getRegistrationDate());
        client.setBirthday(clientDTO.getBirthday());
        return client;
    }

    private ClientDTO transformEntitytoDTO(Client client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setRegistrationDate(client.getRegistrationDate());
        clientDTO.setBirthday(client.getBirthday());
        return clientDTO;
    }
}
