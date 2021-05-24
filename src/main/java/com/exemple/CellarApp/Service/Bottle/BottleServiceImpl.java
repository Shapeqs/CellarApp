package com.exemple.CellarApp.Service.Bottle;

import com.exemple.CellarApp.DTO.BottleDTO;
import com.exemple.CellarApp.Model.Bottle;
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

    /**
     * Le logger de la classe
     */
    private Logger LOGGER = LoggerFactory.getLogger(BottleServiceImpl.class);

    /**
     * Le repository des bouteilles
     */
    @Autowired
    private BottleRepository bottleRepository;

    /**
     * Le service des chateaux
     */
    @Autowired
    private CastelService castelService;

    /**
     * Le service des appelations
     */
    @Autowired
    private NamingService namingService;

    @Override
    public List<Bottle> findAll() {
        ArrayList<Bottle> bottles = new ArrayList<>();
        for (BottleDTO b : bottleRepository.findAll()) {
            bottles.add(transformDTOtoEntity(b));
        }
        return bottles;
    }

    @Override
    public Bottle findOne(Integer id) {
        return transformDTOtoEntity(bottleRepository.findById(id));
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
        LOGGER.info(b.toString());
        LOGGER.info(transformEntitytoDTO(b).toString());
        bottleRepository.modify(id, transformEntitytoDTO(b));
    }

    /**
     * Methode transformant une bouteille DTO en entité bouteille
     *
     * @param bottleDTO la bouteille à transformer
     * @return l'entité transformée
     */
    private Bottle transformDTOtoEntity(BottleDTO bottleDTO) {
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

    /**
     * Methode transformant une entité bouteille en bouteille DTO
     *
     * @param bottle la bouteille à transformer
     * @return l'entité transformée en DTO
     */
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
