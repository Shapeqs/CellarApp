package com.exemple.CellarApp.Service.Client;

import com.exemple.CellarApp.Model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    List<Client> findAll();
}
