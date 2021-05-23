package com.exemple.CellarApp.Service.Naming;


import com.exemple.CellarApp.Model.Naming;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NamingService {

    List<Naming> findAll();

    Naming findOne(Integer id);

    void deleteOne(Integer id);

    void deleteAll();

    void addOne(Naming c);

    void modifyOne(Integer id, Naming c);

    Naming findByName(String name);
}
