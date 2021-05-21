package com.exemple.CellarApp.Repository.Employe;

import com.exemple.CellarApp.DTO.EmployeDTO;
import com.exemple.CellarApp.Model.Employe;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository {

    List<EmployeDTO> findAll();

    EmployeDTO findById(Integer id);

    EmployeDTO findByUsername(String username);

    void addNew(EmployeDTO employe);

    void modify(Integer id, EmployeDTO employe);

    void deleteById(Integer id);

    void deleteAll();
}
