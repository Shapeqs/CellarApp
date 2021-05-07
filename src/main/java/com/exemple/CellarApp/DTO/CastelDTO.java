package com.exemple.CellarApp.DTO;

import java.io.Serializable;

public class CastelDTO implements Serializable {
    private Integer id;
    private String name;

    public CastelDTO() {
    }

    public CastelDTO(Integer id, String name) {
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
        return "CastelDAO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
