package com.exemple.CellarApp.Service.Castel;

import com.exemple.CellarApp.DTO.CastelDTO;
import com.exemple.CellarApp.Model.Castel;
import com.exemple.CellarApp.Repository.Castel.CastelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CastelServiceImpl implements CastelService {

    /**
     * Le logger de la classe
     */
    private Logger LOGGER = LoggerFactory.getLogger(CastelServiceImpl.class);


    /**
     * Le repository de chateau
     */
    @Autowired
    private CastelRepository castelRepository;

    @Override
    public List<Castel> findAll(){
        ArrayList<Castel> castels = new ArrayList<>();
        for (CastelDTO c : castelRepository.findAll()) {
            castels.add(transformDTOtoEntity(c));
        }
        return castels;
    }

    @Override
    public Castel findOne(Integer id) {
        return transformDTOtoEntity(castelRepository.findById(id));
    }

    @Override
    public Castel findByName(String name) {
        return transformDTOtoEntity(castelRepository.findByName(name));
    }

    @Override
    public void deleteOne(Integer id) {
        castelRepository.deleteById(id);
    }

    @Override
    public void deleteAll(){
        castelRepository.deleteAll();
    }


    @Override
    public void addOne(Castel c){
        castelRepository.addNew(transformEntityToDTO(c));
    }

    @Override
    public void modifyOne(Integer id, Castel c){
        Castel castel = new Castel();
        castel.setName(c.getName());
        castelRepository.modify(id, transformEntityToDTO(castel));
    }

    /**
     * Methode transformant un chateau DTO en entité chateau
     *
     * @param castelDTO le chateau à transformer
     * @return l'entité transformée
     */
    private Castel transformDTOtoEntity(CastelDTO castelDTO) {
        Castel c = new Castel();
        c.setId(castelDTO.getId());
        c.setName(castelDTO.getName());
        return c;
    }

    /**
     * Methode transformant une entité chateau en chateau DTO
     *
     * @param castel la bouteille à transformer
     * @return l'entité transformée en DTO
     */
    private CastelDTO transformEntityToDTO(Castel castel) {
        return new CastelDTO(castel.getId(), castel.getName());
    }




}
