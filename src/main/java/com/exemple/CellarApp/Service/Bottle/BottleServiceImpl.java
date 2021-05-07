package com.exemple.CellarApp.Service.Bottle;

import com.exemple.CellarApp.DTO.BottleDTO;
import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Model.Naming;
import com.exemple.CellarApp.Repository.Bottle.BottleRepository;
import com.exemple.CellarApp.Service.Castel.CastelService;
import com.exemple.CellarApp.Service.Naming.NamingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BottleServiceImpl implements BottleService {

    private Logger LOGGER = LoggerFactory.getLogger(BottleServiceImpl.class);

    @Autowired
    private BottleRepository bottleRepository;

    @Autowired
    private CastelService castelService;

    @Autowired
    private NamingService namingService;

    @Override
    public List<Bottle> findAll() {
        ArrayList<Bottle> bottles = new ArrayList<>();
        for (BottleDTO b : bottleRepository.findAll()) {
            bottles.add(transformDAOtoEntity(b));
        }
        return bottles;
    }

    @Override
    public Bottle findOne(Integer id) {
        return transformDAOtoEntity(bottleRepository.findById(id));
    }

    @Override
    public void deleteOne(Integer id) {
        bottleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        bottleRepository.deleteAll();
    }


    @Override
    public void addOne(Bottle b) {
        bottleRepository.addNew(transformEntitytoDTO(b));
    }

    @Override
    public void modifyOne(Integer id, Bottle b) {
        bottleRepository.modify(id, transformEntitytoDTO(b));
    }

    private Bottle transformDAOtoEntity(BottleDTO bottleDTO) {
        Bottle b = new Bottle();
        b.setId(bottleDTO.getId());
        b.setVintage(bottleDTO.getVintage());
        b.setPrice(bottleDTO.getPrice());
        b.setInfos(bottleDTO.getInfos());
        b.setColor(bottleDTO.getColor());
        b.setYear(bottleDTO.getYear());
        b.setQuantity(bottleDTO.getQuantity());
        b.setAlcool(bottleDTO.getAlcool());
        b.setCastel(castelService.findOne(bottleDTO.getCastelId()));
        b.setNaming(namingService.findOne(bottleDTO.getNamingId()));
        return b;
    }

    private BottleDTO transformEntitytoDTO(Bottle bottle) {
        return new BottleDTO(bottle.getId(),
                bottle.getVintage(), bottle.getPrice(),
                bottle.getInfos(), bottle.getColor(),
                bottle.getYear(), bottle.getQuantity(),
                bottle.getAlcool(),
                bottle.getCastel().getId(),
                bottle.getNaming().getId());
    }

}
