package com.exemple.CellarApp.DTO;

/**
 * EntryBottleDTO est la classe représentant l'entité <Bottle,int>
 *     pour serialiser les entry de listBottle dans Order en json
 */
public class EntryBottleDTO {
    private Integer idBottle;
    private Integer quantity;

    public EntryBottleDTO(Integer idBottle, Integer quantity) {
        this.idBottle = idBottle;
        this.quantity = quantity;
    }

    public EntryBottleDTO() {}

    public Integer getIdBottle() {
        return idBottle;
    }

    public void setIdBottle(Integer idBottle) {
        this.idBottle = idBottle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "EntryBottleDTO{" +
                "idBottle=" + idBottle +
                ", quantity=" + quantity +
                '}';
    }
}
