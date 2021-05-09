package com.exemple.CellarApp;

public enum URLs {
    Bottle("Bottles.json"),
    Castel("Castels.json"),
    Naming("Naming.json"),
    Employe("Employes.json"),
    Client("Clients.json"),
    Order("Order.json"),
    User("Users.json"),
    WhiteWineImage("WhiteWine.png"),
    RedWineImage("RedWine.png"),
    PinkWineImage("PinkWine.png"),
    YellowWineImage("YellowWine.png"),
    VintageFont("WineDate.ttf"),
    AlcoolFont("Imperator.ttf");

    public final String url;

    URLs(String url) {
        this.url = "src/main/resources/Database/" + url;
    }
}
