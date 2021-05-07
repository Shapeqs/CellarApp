package com.exemple.CellarApp;

public enum URLs {
    Bottle("Bottles.json"),
    Castel("Castels.json"),
    Naming("Naming.json"),
    Employe("Employes.json"),
    User("Users.json");

    public final String url;

    URLs(String url) {
        this.url = "src/main/resources/Database/" + url;
    }
}
