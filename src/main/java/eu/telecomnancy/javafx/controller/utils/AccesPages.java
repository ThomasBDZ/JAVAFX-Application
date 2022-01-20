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
    private MenuBarConnexion menuBarConnexion = new MenuBarConnexion(profRDV);
    private Connexion connexionController = new Connexion(profRDV);
    private AccueilEtudiant accueilEtudiant = new AccueilEtudiant(profRDV);
    private MenuBarController menuBarController = new MenuBarController(profRDV);
    private Calendrier calendrier = new Calendrier(profRDV);
    private AccueilEnseignant accueilEnseignant = new AccueilEnseignant(profRDV);
    private AccueilAdmin accueilAdmin = new AccueilAdmin(profRDV);

    public AccesPages(ProfRDV profRDV){
        this.profRDV = profRDV;
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
