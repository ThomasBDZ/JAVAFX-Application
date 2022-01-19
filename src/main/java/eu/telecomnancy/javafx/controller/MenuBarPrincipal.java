package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MenuBarPrincipal {
    
    /**
     * Controleur de la vue MenuBar.fxml
     */

    ProfRDV profRDV;

    public MenuBarPrincipal(ProfRDV profRDV){
        this.profRDV = profRDV;
    }
   
    /**
     * Controle du bouton de deconnexion dans le menu bar principal.
     * Si l'utilisateur est connecté.
     * Non affiché si on est pas connecté.
     */
    public void deconnexion(){

    }

    /**
     * Controle du bouton accueil dans le menu bar principale. 
     * --> Renvoit vers la page d'accueil si connecté.
     * --> Ne fait rien sinon.
     */
    public void accueil(){

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
