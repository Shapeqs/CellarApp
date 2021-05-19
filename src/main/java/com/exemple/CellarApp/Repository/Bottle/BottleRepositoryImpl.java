package com.exemple.CellarApp.Repository.Bottle;

import com.exemple.CellarApp.DTO.BottleDTO;
import com.exemple.CellarApp.Model.Bottle;
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
public class BottleRepositoryImpl implements BottleRepository {

    private Logger LOGGER = LoggerFactory.getLogger(BottleRepositoryImpl.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<BottleDTO> findAll() {
        List<BottleDTO> listBottle = new ArrayList<>();
        try {
            listBottle = mapper.readValue(new URL("file:" + URLs.Bottle.url), new TypeReference<>(){});
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return listBottle;
    }

    @Override
    public BottleDTO findById(Integer id) {
        List<BottleDTO> listBottle = findAll();
        for (BottleDTO b : listBottle) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public void addNew(BottleDTO bottle) {
        List<BottleDTO> listBottle = findAll();
        if (!listBottle.isEmpty()) {
            bottle.setId(listBottle.get(listBottle.size() - 1).getId() + 1);
        } else {
            bottle.setId(0);
        }
        listBottle.add(bottle);
        try {
            mapper.writeValue(new File(URLs.Bottle.url), listBottle);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void modify(Integer id, BottleDTO bottle) {
        List<BottleDTO> listBottle = findAll();
        if (!listBottle.isEmpty()) {
            for (BottleDTO b : listBottle) {
                if (b.getId().equals(id)) {
                    b.setVintage(bottle.getVintage());
                    b.setPrice(bottle.getPrice());
                    b.setInfos(bottle.getInfos());
                    b.setColor(bottle.getColor());
                    b.setYear(bottle.getYear());
                    b.setQuantity(bottle.getQuantity());
                    b.setCastelId(bottle.getCastelId());
                    b.setNamingId(bottle.getNamingId());
                    try {
                        mapper.writeValue(new File(URLs.Bottle.url), listBottle);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        List<BottleDTO> listBottle = findAll();
        listBottle.removeIf(b -> b.getId().equals(id));
        try {
            mapper.writeValue(new File(URLs.Bottle.url), listBottle);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            mapper.writeValue(new File(URLs.Bottle.url), new ArrayList<Bottle>());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
