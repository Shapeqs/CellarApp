package com.exemple.CellarApp.Service.Castel;


import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Model.Castel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CastelService {

    List<Castel> findAll();

    Castel findOne(Integer id);

    void deleteOne(Integer id);

    void deleteAll();

    void addOne(Castel c);

    void modifyOne(Integer id, Castel c);

}
