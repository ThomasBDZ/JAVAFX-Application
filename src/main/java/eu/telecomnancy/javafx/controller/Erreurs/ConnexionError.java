package eu.telecomnancy.javafx.controller.Erreurs;

import javafx.scene.control.Label;

public class ConnexionError extends Exception{

    private Label erreur;

    public ConnexionError(String erreur){
        super();
        this.erreur = new Label(erreur);
        this.erreur.setTextFill(Color.RED);
    }

    public Label getError() {
       return this.erreur;
    }



    
}
