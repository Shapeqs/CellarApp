package com.exemple.CellarApp.Model;

import com.exemple.CellarApp.DTO.EmployeDTO;
import com.exemple.CellarApp.Security.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Employe implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String firstname;
    private Date birthDay;
    private String username;
    private String password;
    private UserRoles role;

    public Employe(Integer id,
                   String name,
                   String firstname,
                   Date birthDay,
                   String username,
                   String password,
                   UserRoles role) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.birthDay = birthDay;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Employe(EmployeDTO employeDTO) {
        this.id = employeDTO.getId();
        this.name = employeDTO.getName();
        this.firstname = employeDTO.getFirstname();
        this.birthDay = employeDTO.getBirthDay();
        this.username = employeDTO.getUsername();
        this.password = employeDTO.getPassword();
        this.role = UserRoles.valueOf(employeDTO.getRole());
    }

    public Employe() {
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

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles roles) {
        this.role = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstname + '\'' +
                ", birthDay=" + birthDay +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
