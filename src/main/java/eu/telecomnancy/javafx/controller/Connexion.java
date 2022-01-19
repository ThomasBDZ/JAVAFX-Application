package eu.telecomnancy.javafx.controller;


import eu.telecomnancy.javafx.controller.Erreurs.ConnexionException;
import eu.telecomnancy.javafx.controller.utils.AccesPages;
import eu.telecomnancy.javafx.model.GestionnaireDB.*;
import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.GestionnaireDB.GestionnaireLogin;
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
public class Connexion extends Controlleur {

    private Label erreur;
    private Boolean erreurShown;

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
        super(profRDV);
        this.erreurShown = false;
    }

    /**
     * Méthode pour valider la connexion issue de la vue Connexion.fxml.
     * Gère les erreurs qui peuvent être remontée (MailInexistant et MauvaisMdp)
     */
    public void validationConnexion(){
        String mdpStr = mdp.getText();
        String idStr = id.getText();
    
        GestionnaireLogin gestionnaireLogin = new GestionnaireLogin();
        Node node= (Node) connexion;
        AccesPages accesPages= profRDV.getAccesPages();
        String type = "";
        try {
            type = gestionnaireLogin.login(mdpStr,idStr);
        } catch (ConnexionException e) {
            if(erreurShown){
                vboxConnexion.getChildren().remove(erreur);
            }
            this.erreur = e.getError();
            this.erreurShown = true;
            vboxConnexion.getChildren().add(erreur);
        } 

        switch (type) {
            case "admin":
                accesPages.accesAccueilAdmin(node);
                break;

            case "eleve":
                accesPages.accesAccueilEtudiant(node);
                break;

            case "prof":
                accesPages.accesAccueilEnseignant(node);
                break;
        
        
            default:
                break;
        }
        
    }
    
}
