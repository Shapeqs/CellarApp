package com.exemple.CellarApp.Model;

import com.exemple.CellarApp.Service.listConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Castel")
public class Castel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Convert(converter = listConverter.class)
    private List<Bottle> bottleList;

    public Castel() {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
