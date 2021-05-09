package com.exemple.CellarApp.Repository.Castel;

import com.exemple.CellarApp.DTO.CastelDTO;
import com.exemple.CellarApp.Model.Castel;
import com.exemple.CellarApp.Utils.URLs;
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
public class CastelRepositoryImpl implements CastelRepository {

    private Logger LOGGER = LoggerFactory.getLogger(CastelRepositoryImpl.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<CastelDTO> findAll() {
        List<CastelDTO> listCastel = new ArrayList<>();
        try {
            listCastel = mapper.readValue(new URL("file:" + URLs.Castel.url), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return listCastel;
    }

    @Override
    public CastelDTO findById(Integer id) {
        List<CastelDTO> listCastel = findAll();
        for (CastelDTO c : listCastel) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void addNew(CastelDTO castel) {
        List<CastelDTO> listCastel = findAll();
        if (!listCastel.isEmpty()) {
            castel.setId(listCastel.get(listCastel.size() - 1).getId() + 1);
        } else {
            castel.setId(0);
        }
        listCastel.add(castel);
        try {
            mapper.writeValue(new File(URLs.Castel.url), listCastel);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void modify(Integer id, CastelDTO castel) {
        List<CastelDTO> listCastel = findAll();
        if (!listCastel.isEmpty()) {
            for (CastelDTO c : listCastel) {
                if (c.getId().equals(id)) {
                    c.setName(castel.getName());
                    try {
                        mapper.writeValue(new File(URLs.Castel.url), listCastel);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        List<CastelDTO> listCastel = findAll();
        listCastel.removeIf(c -> c.getId().equals(id));
        try {
            mapper.writeValue(new File(URLs.Castel.url), listCastel);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            mapper.writeValue(new File(URLs.Castel.url), new ArrayList<Castel>());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
