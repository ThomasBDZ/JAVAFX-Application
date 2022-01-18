package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.controller.Erreurs.ConnexionError;
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

        //     GestionnaireDB gestionnaireDB = new GestionnaireDB();
        //     gestionnaireDB.login(mdp,id);
            
        // } catch (Error e){

        // }
        // catch (ConnexionError e) {
        //      Label erreur = e.getError();
        //      vboxConnexion.getChildren().add(erreur);
        //     //TODO: handle exception
        // }

        Node node=(Node) connexion;
        Stage stage =(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(getClass().getResource("/fxml/Calendrier.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 1000);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            //TODO: handle exception
        }


    }
    
}
