package com.exemple.CellarApp.Service.Naming;

import com.exemple.CellarApp.DTO.NamingDTO;
import com.exemple.CellarApp.Model.Naming;
import com.exemple.CellarApp.Repository.Naming.NamingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NamingServiceImpl implements NamingService {

    private Logger LOGGER = LoggerFactory.getLogger(NamingServiceImpl.class);

    @Autowired
    private NamingRepository namingRepository;

    @Override
    public List<Naming> findAll(){
        ArrayList<Naming> namings = new ArrayList<>();
        for (NamingDTO n : namingRepository.findAll()) {
            namings.add(transformDAOtoEntity(n));
        }
        return namings;
    }

    @Override
    public Naming findOne(Integer id) {
        return transformDAOtoEntity(namingRepository.findById(id));
    }

    @Override
    public void deleteOne(Integer id) {
        namingRepository.deleteById(id);
    }

    @Override
    public void deleteAll(){
        namingRepository.deleteAll();
    }


    @Override
    public void addOne(Naming n){
        namingRepository.addNew(transformEntityToDAO(n));
    }

    @Override
    public void modifyOne(Integer id, Naming n){
        Naming naming = new Naming();
        naming.setName(n.getName());
        namingRepository.modify(id, transformEntityToDAO(naming));
    }

    @Override
    public Naming findByName(String name) {
        return transformDAOtoEntity(namingRepository.findByName(name));
    }

    private Naming transformDAOtoEntity(NamingDTO namingDTO) {
        Naming n = new Naming();
        n.setId(namingDTO.getId());
        n.setName(namingDTO.getName());
        return n;
    }

    private NamingDTO transformEntityToDAO(Naming naming) {
        return new NamingDTO(naming.getId(), naming.getName());
    }




}
