package eu.telecomnancy.javafx.controller;


import eu.telecomnancy.javafx.controller.Erreurs.ConnexionError;
import eu.telecomnancy.javafx.controller.utils.AccesPages;
import eu.telecomnancy.javafx.model.GestionnaireLogin;
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
        this.profRDV = profRDV;
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
        Node node=(Node) connexion;
        String path = "/fxml/";
        AccesPages accesPages= profRDV.getAccesPages();
        String type = "";
        try {
            type = gestionnaireLogin.login(mdpStr,idStr);
        } catch (ConnexionError e) {
            if(erreurShown){
                vboxConnexion.getChildren().remove(erreur);
            }
            this.erreur = e.getError();
            this.erreurShown = true;
            vboxConnexion.getChildren().add(erreur);
            
            //TODO: handle exception
        } 

        switch (type) {
            case "admin":
                path= path + "AccueilAdmin.fxml";
                accesPages.accesAccueilAdmin(node);
                break;

            case "eleve":
                path= path + "AccueilEtudiant.fxml";
                accesPages.accesAccueilEtudiant(node);
                break;

            case "prof":
                path= path + "AccueilEnseignant.fxml";
                accesPages.accesAccueilEnseignant(node);
                break;
        
        
            default:
                break;
        }
        
    }
    
}
