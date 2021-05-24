package com.exemple.CellarApp.EnumUtils;

/**
 * URLs des ressources
 */
public enum URLs {
    /**
     * URL du json des bouteilles
     */
    Bottle("Model/Bottles.json"),
    /**
     * URL du json des chateaux
     */
    Castel("Model/Castels.json"),
    /**
     * URL du json des appelations
     */
    Naming("Model/Naming.json"),
    /**
     * URL du json des clients
     */
    Client("Model/Clients.json"),
    /**
     * URL du json des commandes
     */
    Order("Model/Order.json"),
    /**
     * URL du json des utilisateurs
     */
    User("Model/User.json"),
    /**
     * URL de l'image d'une bouteille de blanc
     */
    WhiteWineImage("Img/White.png"),
    /**
     * URL de l'image d'une bouteille de rouge
     */
    RedWineImage("Img/Red.png"),
    /**
     * URL de l'image d'une bouteille de rosé
     */
    PinkWineImage("Img/Pink.png"),
    /**
     * URL de l'image d'une bouteille de mouelleux
     */
    YellowWineImage("Img/Yellow.png"),
    /**
     * URL de la police d'écriture
     */
    Font("Font/Mermaid1001.ttf");

    /**
     * URL complète
     */
    public final String url;

    /**
     * Methode retournant l'URL complète jusqu'au ressource
     *
     * @param url le fichier en lui-même
     */
    URLs(String url) {
        this.url = "src/main/resources/Database/" + url;
    }
}
