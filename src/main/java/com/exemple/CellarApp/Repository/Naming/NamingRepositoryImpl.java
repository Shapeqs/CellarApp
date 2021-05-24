package com.exemple.CellarApp.Repository.Naming;

import com.exemple.CellarApp.DTO.NamingDTO;
import com.exemple.CellarApp.Model.Castel;
import com.exemple.CellarApp.EnumUtils.URLs;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NamingRepositoryImpl implements NamingRepository {

    /**
     * Le logger de la classe
     */
    private Logger LOGGER = LoggerFactory.getLogger(NamingRepositoryImpl.class);
    /**
     * Le mapper pour transformer les entités en json
     */
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<NamingDTO> findAll() {
        List<NamingDTO> listCalling = new ArrayList<>();
        try {
            listCalling = mapper.readValue(new URL("file:" + URLs.Naming.url), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return listCalling;
    }

    @Override
    public NamingDTO findById(Integer id) {
        List<NamingDTO> listCalling = findAll();
        for (NamingDTO c : listCalling) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void addNew(NamingDTO namingDTO) {
        List<NamingDTO> listCalling = findAll();
        if (!listCalling.isEmpty()) {
            namingDTO.setId(listCalling.get(listCalling.size() - 1).getId() + 1);
        } else {
            namingDTO.setId(0);
        }
        listCalling.add(namingDTO);
        try {
            mapper.writeValue(new File(URLs.Naming.url), listCalling);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void modify(Integer id, NamingDTO namingDTO) {
        List<NamingDTO> listCalling = findAll();
        if (!listCalling.isEmpty()) {
            for (NamingDTO c : listCalling) {
                if (c.getId().equals(id)) {
                    c.setName(namingDTO.getName());
                    try {
                        mapper.writeValue(new File(URLs.Naming.url), listCalling);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        List<NamingDTO> listCalling = findAll();
        listCalling.removeIf(c -> c.getId().equals(id));
        try {
            mapper.writeValue(new File(URLs.Naming.url), listCalling);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            mapper.writeValue(new File(URLs.Naming.url), new ArrayList<Castel>());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public NamingDTO findByName(String name) {
        List<NamingDTO> listCalling = findAll();
        for (NamingDTO c : listCalling) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
