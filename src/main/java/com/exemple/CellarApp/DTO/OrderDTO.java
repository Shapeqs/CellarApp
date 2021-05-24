package com.exemple.CellarApp.DTO;

import java.util.Date;
import java.util.List;

/**
 * OrderDTO est la classe représentant l'entité Order pour serialiser Order en json
 */
public class OrderDTO {

    private Integer id;

    private Date orderDate;

    private Integer idClient;

    private List<EntryBottleDTO>listBottles;

    public OrderDTO(Integer id, Date orderDate, Integer idClient, List<EntryBottleDTO> listBottles) {
        this.id = id;
        this.orderDate = orderDate;
        this.idClient = idClient;
        this.listBottles = listBottles;
    }

    public OrderDTO() { }

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

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public List<EntryBottleDTO> getListBottles() {
        return listBottles;
    }

    public void setListBottles(List<EntryBottleDTO> listBottles) {
        this.listBottles = listBottles;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", idClient=" + idClient +
                ", listBottles=" + listBottles +
                '}';
    }
}
