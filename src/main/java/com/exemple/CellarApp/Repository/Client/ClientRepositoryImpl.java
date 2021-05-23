package com.exemple.CellarApp.Repository.Client;

import com.exemple.CellarApp.DTO.ClientDTO;
import com.exemple.CellarApp.Model.Client;
import com.exemple.CellarApp.EnumUtils.URLs;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository{

    private Logger LOGGER = LoggerFactory.getLogger(ClientRepositoryImpl.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<ClientDTO> findAll() {
        List<ClientDTO> listClient = new ArrayList<>();
        try {
            listClient = mapper.readValue(new URL("file:" + URLs.Client.url), new TypeReference<>(){});
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return listClient;
    }

    @Override
    public ClientDTO findById(Integer id) {
        List<ClientDTO> listClient = findAll();
        for (ClientDTO c : listClient) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void addNew(ClientDTO client) {
        List<ClientDTO> listClient = findAll();
        if (!listClient.isEmpty()) {
            client.setId(listClient.get(listClient.size() - 1).getId() + 1);
        } else {
            client.setId(0);
        }
        listClient.add(client);
        try {
            mapper.writeValue(new File(URLs.Client.url), listClient);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void modify(Integer id, ClientDTO client) {
        List<ClientDTO> listClient = findAll();
        if (!listClient.isEmpty()) {
            for (ClientDTO c : listClient) {
                if (c.getId().equals(id)) {
                    c.setFirstName(client.getFirstName());
                    c.setLastName(client.getLastName());
                    c.setBirthday(client.getBirthday());
                    c.setRegistrationDate(client.getRegistrationDate());
                    c.setEmail(client.getEmail());
                    c.setPhoneNumber(client.getPhoneNumber());
                    c.setTypeClient(client.getTypeClient());
                    c.setsiren(client.getsiren());
                    try {
                        mapper.writeValue(new File(URLs.Client.url), listClient);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        List<ClientDTO> listClient = findAll();
        listClient.removeIf(b -> b.getId().equals(id));
        try {
            mapper.writeValue(new File(URLs.Client.url), listClient);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            mapper.writeValue(new File(URLs.Client.url), new ArrayList<Client>());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
