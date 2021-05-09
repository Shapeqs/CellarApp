package com.exemple.CellarApp.Utils;

public enum URLs {
    Bottle("Model/Bottles.json"),
    Castel("Model/Castels.json"),
    Naming("Model/Naming.json"),
    Employe("Model/Employes.json"),
    Client("Model/Clients.json"),
    Order("Model/Order.json"),
    User("Model/Users.json"),
    WhiteWineImage("Img/WhiteWine.png"),
    RedWineImage("Img/RedWine.png"),
    PinkWineImage("Img/PinkWine.png"),
    YellowWineImage("Img/YellowWine.png"),
    VintageFont("Font/WineDate.ttf"),
    AlcoolFont("Font/Imperator.ttf");

    public final String url;

    URLs(String url) {
        this.url = "src/main/resources/Database/" + url;
    }
}
