package com.exemple.CellarApp.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Bottle implements Serializable {
    @Id
    private Integer id;

    private String name;
    private String infos;
    private int year;
    private double price;
    private int quantity;

    public Bottle() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Bottle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", infos='" + infos + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
