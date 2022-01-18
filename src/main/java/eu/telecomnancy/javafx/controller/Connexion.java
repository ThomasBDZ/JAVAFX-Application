package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.controller.Erreurs.ConnexionError;
import eu.telecomnancy.javafx.model.GestionnaireDB;
import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
    /**
     * Controleur de la vue Connexion.fxml
     */
   
public class Connexion {

    private ProfRDV profRDV;

    @FXML
    private Vbox vboxConnexion;

    @FXML
    private MenuItem menuItemAccueil;

    @FXML
    private TextField id;

    @FXML
    private PasswordField mdp;


    public Connexion(ProfRDV profRDV){
        this.profRDV = profRDV;
    }

    /**
     * Méthode pour valider la connexion issue de la vue Connexion.fxml.
     * Gère les erreurs qui peuvent être remontée (MailInexistant et MauvaisMdp)
     */
    public void validationConnexion(){
        try {

            GestionnaireDB gestionnaireDB = new GestionnaireDB();
            gestionnaireDB.login(mdp,id);
            
        } catch (ConnexionError e) {
             Label erreur = e.getError();
             vboxConnexion.getChildren().add(erreur);
            //TODO: handle exception
        }
    }
    
}
