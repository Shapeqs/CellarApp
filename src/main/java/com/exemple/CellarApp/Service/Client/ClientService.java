package com.exemple.CellarApp.Service.Client;

import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    List<Client> findAll();

    Client findOne(Integer id);

    void deleteOne(Integer id);

    void deleteAll();

    void addOne(Client c);

    void modifyOne(Integer id, Client c);
}
