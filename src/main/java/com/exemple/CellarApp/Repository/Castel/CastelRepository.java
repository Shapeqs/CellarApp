package com.exemple.CellarApp.Repository.Castel;

import com.exemple.CellarApp.DTO.CastelDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastelRepository {

    List<CastelDTO> findAll();

    CastelDTO findById(Integer id);

    void addNew(CastelDTO castel);

    void modify(Integer id, CastelDTO castel);

    void deleteById(Integer id);

    void deleteAll();

}
