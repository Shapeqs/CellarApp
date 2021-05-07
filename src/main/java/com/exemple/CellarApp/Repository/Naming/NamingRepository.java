package com.exemple.CellarApp.Repository.Naming;

import com.exemple.CellarApp.DTO.NamingDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NamingRepository {

    List<NamingDTO> findAll();

    NamingDTO findById(Integer id);

    void addNew(NamingDTO namingDTO);

    void modify(Integer id, NamingDTO namingDTO);

    void deleteById(Integer id);

    void deleteAll();

}
