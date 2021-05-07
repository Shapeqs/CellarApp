package com.exemple.CellarApp.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Bottle implements Serializable {
    @Id
    private Integer id;

    private String vintage;
    private int year;
    private String infos;
    private Color color;
    private double price;
    private double alcool;
    private int quantity;

    @ManyToOne
    private Castel castel;
    @ManyToOne
    private Naming naming;

    public Bottle() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String cuvee) {
        this.vintage = cuvee;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAlcool() {
        return alcool;
    }

    public void setAlcool(double alcool) {
        this.alcool = alcool;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Castel getCastel() {
        return castel;
    }

    public void setCastel(Castel castel) {
        this.castel = castel;
    }

    public Naming getNaming() {
        return naming;
    }

    public void setNaming(Naming naming) {
        this.naming = naming;
    }

    @Override
    public String toString() {
        return "Bottle{" +
                "id=" + id +
                ", name='" + vintage + '\'' +
                ", year=" + year +
                ", infos='" + infos + '\'' +
                ", color=" + color +
                ", price=" + price +
                ", alcool=" + alcool +
                ", quantity=" + quantity +
                ", castel=" + castel +
                ", naming=" + naming +
                '}';
    }
}
