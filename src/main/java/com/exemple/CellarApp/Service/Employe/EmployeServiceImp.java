package com.exemple.CellarApp.Service.Employe;

import com.exemple.CellarApp.Model.Employe;
import com.exemple.CellarApp.Repository.Employe.EmployeRepository;
import com.exemple.CellarApp.Service.Bottle.BottleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeServiceImp implements EmployeService {

    private Logger LOGGER = LoggerFactory.getLogger(BottleServiceImpl.class);

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public List<Employe> findAll() {
        return new ArrayList<>(employeRepository.findAll());
    }

    @Override
    public Employe findOneById(Integer id) {
        return employeRepository.findById(id);
    }

    @Override
    public Employe findOneByLogin(String username) {
        return employeRepository.findByUsername(username);
    }

    @Override
    public void deleteOne(Integer id) {
        employeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        employeRepository.deleteAll();
    }

    @Override
    public void addOne(Employe e) {
        employeRepository.addNew(e);
    }

    @Override
    public void modifyOne(Integer id, Employe e) {
        List<Employe> list = findAll();
        list.removeIf(u -> !u.getId().equals(id));
        if (!list.isEmpty()) {
            employeRepository.modify(id, e);
        }
    }
}
