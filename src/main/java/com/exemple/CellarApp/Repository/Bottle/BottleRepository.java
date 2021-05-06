package com.exemple.CellarApp.Repository.Bottle;

import com.exemple.CellarApp.Model.Bottle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BottleRepository {

    List<Bottle> findAll();

    Bottle findById(Integer id);

    void addNew(Bottle bottle);

    void modify(Integer id, Bottle bottle);

    void deleteById(Integer id);

    void deleteAll();

}
