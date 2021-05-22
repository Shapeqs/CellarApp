package com.exemple.CellarApp.DTO;

import com.exemple.CellarApp.Model.User;

import java.sql.Date;

public class UserDTO {
    private Integer id;
    private String name;
    private String firstname;
    private Date birthday;
    private String username;
    private String password;
    private String role;

    public UserDTO(Integer id,
                   String name,
                   String firstname,
                   Date birthday,
                   String username,
                   String password,
                   String role) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.firstname = user.getFirstname();
        this.birthday = user.getBirthday();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole().name();
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
                ", birthday=" + birthday +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + role + '\'' +
                '}';
    }
}
