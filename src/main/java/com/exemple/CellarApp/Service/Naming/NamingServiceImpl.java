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

    /**
     * Le logger de la classe
     */
    private Logger LOGGER = LoggerFactory.getLogger(NamingServiceImpl.class);

    /**
     * Le repository des appelations
     */
    @Autowired
    private NamingRepository namingRepository;

    @Override
    public List<Naming> findAll(){
        ArrayList<Naming> namings = new ArrayList<>();
        for (NamingDTO n : namingRepository.findAll()) {
            namings.add(transformDTOtoEntity(n));
        }
        return namings;
    }

    @Override
    public Naming findOne(Integer id) {
        return transformDTOtoEntity(namingRepository.findById(id));
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
        namingRepository.addNew(transformEntityToDTO(n));
    }

    @Override
    public void modifyOne(Integer id, Naming n){
        Naming naming = new Naming();
        naming.setName(n.getName());
        namingRepository.modify(id, transformEntityToDTO(naming));
    }

    @Override
    public Naming findByName(String name) {
        return transformDTOtoEntity(namingRepository.findByName(name));
    }

    /**
     * Methode transformant une appelation DTO en entité appelation
     *
     * @param namingDTO l'appelation à transformer
     * @return l'entité transformée
     */
    private Naming transformDTOtoEntity(NamingDTO namingDTO) {
        Naming n = new Naming();
        n.setId(namingDTO.getId());
        n.setName(namingDTO.getName());
        return n;
    }

    /**
     * Methode transformant une entité appelation en appelation DTO
     *
     * @param naming l'appelation à transformer
     * @return l'entité transformée en DTO
     */
    private NamingDTO transformEntityToDTO(Naming naming) {
        return new NamingDTO(naming.getId(), naming.getName());
    }




}
