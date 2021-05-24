package com.exemple.CellarApp.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Entité Order représentant une commande
 */
@Entity
public class Order implements Serializable {
    @Id
    private Integer id;

    private Date orderDate;

    @ManyToOne
    private Client client;

    private HashMap<Bottle, Integer> listBottles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public HashMap<Bottle, Integer> getListBottles() {
        return listBottles;
    }

    public void setListBottles(HashMap<Bottle, Integer> listBottle) {
        this.listBottles = listBottle;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", client=" + client +
                ", listBottles=" + listBottles +
                '}';
    }
}
