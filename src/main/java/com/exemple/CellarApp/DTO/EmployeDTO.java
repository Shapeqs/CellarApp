package com.exemple.CellarApp.DTO;

import com.exemple.CellarApp.Model.Employe;

import java.sql.Date;

public class EmployeDTO {
    private Integer id;
    private String name;
    private String firstname;
    private Date birthDay;
    private String username;
    private String password;
    private String role;

    public EmployeDTO(Integer id,
                      String name,
                      String firstname,
                      Date birthDay,
                      String username,
                      String password,
                      String role) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.birthDay = birthDay;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public EmployeDTO() {
    }

    public EmployeDTO(Employe employe) {
        this.id = employe.getId();
        this.name = employe.getName();
        this.firstname = employe.getFirstname();
        this.birthDay = employe.getBirthDay();
        this.username = employe.getUsername();
        this.password = employe.getPassword();
        this.role = employe.getRole().name();
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "EmployeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", birthDay=" + birthDay +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + role + '\'' +
                '}';
    }
}
