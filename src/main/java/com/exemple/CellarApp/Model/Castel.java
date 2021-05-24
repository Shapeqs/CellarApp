package com.exemple.CellarApp.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Entité Castel représentant un chateau
 */
@Entity
public class Castel implements Serializable {
    @Id
    private Integer id;
    private String name;

    public Castel() {}

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
        return "Castel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
