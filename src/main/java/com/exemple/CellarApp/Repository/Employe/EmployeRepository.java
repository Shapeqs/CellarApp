package com.exemple.CellarApp.Repository.Employe;

import com.exemple.CellarApp.Model.Employe;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository {

    List<Employe> findAll();

    Employe findById(Integer id);

    Employe findByUsername(String username);

    void addNew(Employe employe);

    void modify(Integer id, Employe employe);

    void deleteById(Integer id);

    void deleteAll();
}
