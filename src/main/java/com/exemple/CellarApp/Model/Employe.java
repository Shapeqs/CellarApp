package com.exemple.CellarApp.Model;

import javax.persistence.*;

@Entity
@Table(name = "Employe")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;

    private String password;
}
