package eu.telecomnancy.javafx.controller;


import eu.telecomnancy.javafx.controller.Erreurs.ConnexionException;
import eu.telecomnancy.javafx.controller.utils.AccesPages;
import eu.telecomnancy.javafx.model.GestionnaireDB.*;
import eu.telecomnancy.javafx.model.Etudiant;
import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.Utilisateur;
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
        AccesPages accesPages= profRDV.getAccesPages();
        String type = "";
        try {
            type = gestionnaireLogin.login(idStr,mdpStr);
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
                accesPages.accesAccueilAdmin();
                break;

            case "eleve":
                try {
                    int idEleve = PickUser.Pick(idStr);
                    profRDV.utilisateur = GetterUser.getInfoEleve(idEleve);
                    System.out.println(profRDV.utilisateur.birthDate);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                accesPages.accesAccueilEtudiant();
                break;

            case "prof":
                try {
                    int idProf = PickUser.Pick(idStr);
                    profRDV.utilisateur = GetterUser.getInfoProf(idProf);
                } catch (Exception e) {
                   System.err.println(e.getMessage());
                }
                accesPages.accesAccueilEnseignant();
                break;
        
        
            default:
                break;
        }
        
    }
    
}
