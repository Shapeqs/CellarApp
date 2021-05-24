package com.exemple.CellarApp.DTO;

import java.io.Serializable;

/**
 * NamingDTO est la classe représentant l'entité Naming pour serialiser Naming en json
 */
public class NamingDTO implements Serializable {
    private Integer id;
    private String name;

    public NamingDTO() {
    }

    public NamingDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CallingDAO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
