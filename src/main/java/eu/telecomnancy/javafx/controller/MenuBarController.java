package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.controller.utils.AccesPages;
import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.ResourceBundle.Control;



/**
 * Controleur de la vue MenuBar.fxml
 */
public class MenuBarController extends Controlleur {


    public MenuBarController(ProfRDV profRDV){
        super(profRDV);
        this.profRDV = profRDV;
    }
   
    /**
     * Controle du bouton de deconnexion dans le menu bar principal.
     * Si l'utilisateur est connecté.
     * Non affiché si on est pas connecté.
     */

    public void deconnexion(){
        profRDV.getAccesPages().accesAccueilConnexion();
    }

    /**
     * Controle du bouton accueil dans le menu bar principale. 
     * --> Renvoit vers la page d'accueil si connecté.
     * --> Ne fait rien sinon.
     */
    public void accueil(){
        String type = profRDV.getInstance();
        AccesPages accesPages = profRDV.getAccesPages();
        switch (type) {
            case "admin":
                accesPages.accesAccueilAdmin();
                break;

            case "eleve":
                accesPages.accesAccueilEtudiant();
                break;

            case "prof":
                accesPages.accesAccueilEnseignant();
                break;
        
        
            default:
                break;
        }
    }

     /**
     * Controle du bouton "changer de mot de passe" dans le menu bar principale. 
     * --> Affiche la page pour changer son mot de passe
     */
    public void changeMdp(){
        profRDV.getAccesPages().AccesUtilisateurModifierMdp();
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
