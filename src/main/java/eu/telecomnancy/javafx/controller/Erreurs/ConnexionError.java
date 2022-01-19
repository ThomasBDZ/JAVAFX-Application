package eu.telecomnancy.javafx.controller.Erreurs;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ConnexionError extends Exception{

    private Label erreur;

    public ConnexionError(){
        super();
        this.erreur = new Label("Mauvais Mail ou Mot de Passe !");
        this.erreur.setTextFill(Color.RED);
    }

    public Label getError() {
       return this.erreur;
    }
    
}
