package com.exemple.CellarApp.Repository.Client;

import com.exemple.CellarApp.DTO.BottleDTO;
import com.exemple.CellarApp.DTO.ClientDTO;
import com.exemple.CellarApp.Repository.Bottle.BottleRepositoryImpl;
import com.exemple.CellarApp.URLs;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

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
}
