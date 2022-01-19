package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MenuBarConnexion extends Controlleur{

    public MenuBarConnexion(ProfRDV profRDV){
        super(profRDV);
        this.profRDV = profRDV;
    }

    /**
    * Controle du bouton quitter dans le menu bar principale.
     * --> Quitte la page.
     */
    public void quitter(){
        ButtonType stay = new ButtonType("Rester", ButtonBar.ButtonData.YES);
        ButtonType leave = new ButtonType("Quitter", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Voulez-vous vraiment quitter?",leave,stay);
        alert.setTitle("Quitter");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.orElse(leave) == leave) {
            Platform.exit();
        }
    }
}
