package com.exemple.CellarApp.Service.Employe;

import com.exemple.CellarApp.Model.Employe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeService {

    List<Employe> findAll();

    Employe findOneById(Integer id);

    Employe findOneByLogin(String username);

    void deleteOne(Integer id);

    void deleteAll();

    void addOne(Employe e);

    void modifyOne(Integer id, Employe e);
}
