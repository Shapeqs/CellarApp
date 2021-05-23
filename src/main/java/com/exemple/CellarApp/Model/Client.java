package com.exemple.CellarApp.Model;

import com.exemple.CellarApp.EnumUtils.TypeClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Client implements Serializable {
    @Id
    private Integer id;

    private String firstName;
    private String lastName;
    private Date birthday;
    private Date registrationDate;

    private String phoneNumber;
    private String email;
    private TypeClient typeClient;
    private String siren;

    public Client() {
    }

    public Client(Integer id, String firstName, String lastName, Date birthday, Date registrationDate, String phoneNumber, String email, TypeClient typeClient, String siren) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.typeClient = typeClient;
        this.siren = siren;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TypeClient getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(TypeClient typeClient) {
        this.typeClient = typeClient;
    }

    public String getsiren() {
        return siren;
    }

    public void setsiren(String siren) {
        this.siren = siren;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", registrationDate=" + registrationDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", typeClient=" + typeClient +
                ", siren='" + siren + '\'' +
                '}';
    }
}
