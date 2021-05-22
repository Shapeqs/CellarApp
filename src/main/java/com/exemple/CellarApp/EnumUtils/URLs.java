package com.exemple.CellarApp.EnumUtils;

public enum URLs {
    Bottle("Model/Bottles.json"),
    Castel("Model/Castels.json"),
    Naming("Model/Naming.json"),
    Client("Model/Clients.json"),
    Order("Model/Order.json"),
    User("Model/User.json"),
    WhiteWineImage("Img/White.png"),
    RedWineImage("Img/Red.png"),
    PinkWineImage("Img/Pink.png"),
    YellowWineImage("Img/Yellow.png"),
    Font("Font/Mermaid1001.ttf");

    public final String url;

    URLs(String url) {
        this.url = "src/main/resources/Database/" + url;
    }
}
