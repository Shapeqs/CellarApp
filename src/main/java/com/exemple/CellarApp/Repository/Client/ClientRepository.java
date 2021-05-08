package com.exemple.CellarApp.Repository.Client;

import com.exemple.CellarApp.DTO.BottleDTO;
import com.exemple.CellarApp.DTO.ClientDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository {
    List<ClientDTO> findAll();

    ClientDTO findById(Integer id);

    void addNew(ClientDTO client);

    void modify(Integer id, ClientDTO client);

    void deleteById(Integer id);

    void deleteAll();
}
