package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.controller.Erreurs.ConnexionError;
import eu.telecomnancy.javafx.model.AccesAccueil;
import eu.telecomnancy.javafx.model.GestionnaireDB;
import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
    /**
     * Controleur de la vue Connexion.fxml
     */
   
public class Connexion {

    private ProfRDV profRDV;

    @FXML
    private VBox vboxConnexion;

    @FXML
    private MenuItem menuItemAccueil;

    @FXML
    private TextField id;

    @FXML
    private PasswordField mdp;

    @FXML
    private Button connexion;


    public Connexion(ProfRDV profRDV){
        this.profRDV = profRDV;
    }

    /**
     * Méthode pour valider la connexion issue de la vue Connexion.fxml.
     * Gère les erreurs qui peuvent être remontée (MailInexistant et MauvaisMdp)
     */
    public void validationConnexion(){
        // try {
    
        GestionnaireDB gestionnaireDB = new GestionnaireDB();
        String type = "enseignant";
        Node node=(Node) connexion;
        String path = "/fxml/";
        AccesAccueil accesAccueil = new AccesAccueil(profRDV);
        //     String type = gestionnaireDB.login(mdp,id);
        switch (type) {
            case "admin":
                path= path + "AccueilAdmin.fxml";
                break;

            case "eleve":
                path= path + "AccueilEtudiant.fxml";
                break;

            case "enseignant":
                path= path + "AccueilEnseignant.fxml";
                break;
        
        
            default:
                break;
        }
        
        accesAccueil.accesAccueil(node,path);
        // } catch (Error e){

        // }
        // catch (ConnexionError e) {
        //      Label erreur = e.getError();
        //      vboxConnexion.getChildren().add(erreur);
        //     //TODO: handle exception
        // }

        
    }
    
}
