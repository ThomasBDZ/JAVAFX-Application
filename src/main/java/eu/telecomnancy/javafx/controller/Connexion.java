package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.controller.Erreurs.ConnexionError;
import eu.telecomnancy.javafx.model.ProfRDV;
    /**
     * Controleur de la vue Connexion.fxml
     */
   
public class Connexion {

    ProfRDV profRDV;

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
