package eu.telecomnancy.javafx.controller.utils;


import eu.telecomnancy.javafx.controller.*;
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

public class AccesPages {

    private ProfRDV profRDV;
    private MenuBarConnexion menuBarConnexion;
    private Connexion connexionController;
    private AccueilEtudiant accueilEtudiant;
    private MenuBarController menuBarController;
    private Calendrier calendrier;
    private AccueilEnseignant accueilEnseignant;
    private AccueilAdmin accueilAdmin;

    public AccesPages(ProfRDV profRDV){
        this.profRDV = profRDV;
        menuBarConnexion = new MenuBarConnexion(profRDV);
        connexionController = new Connexion(profRDV);
        accueilEtudiant = new AccueilEtudiant(profRDV);
        menuBarController = new MenuBarController(profRDV);
        calendrier = new Calendrier(profRDV);
        accueilEnseignant = new AccueilEnseignant(profRDV);
        accueilAdmin = new AccueilAdmin(profRDV);
    }

    public void loadPage(String path){
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(getClass().getResource(path));
        Stage stage = profRDV.getStage();
        loader.setControllerFactory(ic -> {
        if (ic.equals(eu.telecomnancy.javafx.controller.Connexion.class)) return connexionController;
        if (ic.equals(eu.telecomnancy.javafx.controller.MenuBarConnexion.class)) return menuBarConnexion;
        if (ic.equals(eu.telecomnancy.javafx.controller.MenuBarController.class)) return menuBarController;
        if (ic.equals(eu.telecomnancy.javafx.controller.AccueilEtudiant.class)) return accueilEtudiant;
        if (ic.equals(eu.telecomnancy.javafx.controller.Calendrier.class)) return calendrier;
        if (ic.equals(eu.telecomnancy.javafx.controller.AccueilEnseignant.class)) return accueilEnseignant;
        if (ic.equals(eu.telecomnancy.javafx.controller.AccueilAdmin.class)) return accueilAdmin;
        else return null ;
        });
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 1000);
            // scene.getStylesheets().add(getClass().getResource("/stylesheet/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("exception chargement page " + path );
            System.out.println(e.getMessage());
        }

    }
    
    public void accesAccueilConnexion(){
        String path = "/fxml/Connexion.fxml";
        loadPage(path);
        profRDV.reinitializeInstance();
    }


    public void accesAccueilEtudiant(){       
        String path = "/fxml/AccueilEtudiant.fxml";
        loadPage(path);
        profRDV.setInstance("eleve");
    }

    public void accesAccueilEnseignant(){
        String path = "/fxml/AccueilEnseignant.fxml";
        loadPage(path);
        profRDV.setInstance("prof");
    }

    public void accesAccueilAdmin(){
        String path = "/fxml/AccueilAdmin.fxml";
        loadPage(path);
        profRDV.setInstance("admin");
    }


}
