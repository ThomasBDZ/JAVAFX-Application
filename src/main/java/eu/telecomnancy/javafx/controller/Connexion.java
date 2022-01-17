package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.controller.Erreurs.ConnexionError;
import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.scene.control.MenuItem;
import javafx.fxml.FXML;
    /**
     * Controleur de la vue Connexion.fxml
     */
   
public class Connexion {

    ProfRDV profRDV;

    @FXML
    MenuItem menuItemAccueil;

    public Connexion(ProfRDV profRDV){
        this.profRDV = profRDV;
    }

    /**
     * Méthode pour valider la connexion issue de la vue Connexion.fxml.
     * Gère les erreurs qui peuvent être remontée (MailInexistant et MauvaisMdp)
     */
    public void validationConnexion(){

        // try {
            
        // } catch (ConnexionError e) {
        //     //TODO: handle exception
        // }
    }
    


    
}
