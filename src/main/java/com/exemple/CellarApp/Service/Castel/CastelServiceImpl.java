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

    private Logger LOGGER = LoggerFactory.getLogger(CastelServiceImpl.class);

    @Autowired
    private CastelRepository castelRepository;

    @Override
    public List<Castel> findAll(){
        ArrayList<Castel> castels = new ArrayList<>();
        for (CastelDTO c : castelRepository.findAll()) {
            castels.add(transformDAOtoEntity(c));
        }
        return castels;
    }

    @Override
    public Castel findOne(Integer id) {
        return transformDAOtoEntity(castelRepository.findById(id));
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
        castelRepository.addNew(transformEntityToDAO(c));
    }

    @Override
    public void modifyOne(Integer id, Castel c){
        Castel castel = new Castel();
        castel.setName(c.getName());
        castelRepository.modify(id, transformEntityToDAO(castel));
    }

    private Castel transformDAOtoEntity(CastelDTO castelDTO) {
        Castel c = new Castel();
        c.setId(castelDTO.getId());
        c.setName(castelDTO.getName());
        return c;
    }

    private CastelDTO transformEntityToDAO(Castel castel) {
        return new CastelDTO(castel.getId(), castel.getName());
    }




}
