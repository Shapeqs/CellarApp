package com.exemple.CellarApp.Repository.Client;

import com.exemple.CellarApp.DTO.ClientDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository {
    List<ClientDTO> findAll();
}
