package com.exemple.CellarApp.Service;

import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Repository.Bottle.BottleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BottleServiceImpl implements com.exemple.CellarApp.Service.BottleService {

    private Logger LOGGER = LoggerFactory.getLogger(BottleServiceImpl.class);

    @Autowired
    private BottleRepository bottleRepository;

    @Override
    public List<Bottle> findAll(){
        return bottleRepository.findAll();
    }

    @Override
    public Bottle findOne(Integer id) {
        return bottleRepository.findById(id);
    }

    @Override
    public void deleteOne(Integer id) {
        bottleRepository.deleteById(id);
    }

    @Override
    public void deleteAll(){
        bottleRepository.deleteAll();
    }


    @Override
    public void addOne(Bottle b){
        Bottle bottle = new Bottle();
        bottle.setName(b.getName());
        bottle.setInfos(b.getInfos());
        bottle.setPrice(b.getPrice());
        bottle.setQuantity(b.getQuantity());
        bottle.setYear(b.getYear());
        bottleRepository.addNew(bottle);
    }

    @Override
    public void modifyOne(Integer id, Bottle b){
        Bottle bottle = new Bottle();
        bottle.setName(b.getName());
        bottle.setInfos(b.getInfos());
        bottle.setPrice(b.getPrice());
        bottle.setQuantity(b.getQuantity());
        bottle.setYear(b.getYear());
        bottleRepository.modify(id, bottle);
    }


}
