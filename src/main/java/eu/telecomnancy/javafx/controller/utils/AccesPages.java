package eu.telecomnancy.javafx.controller.utils;


import eu.telecomnancy.javafx.controller.AccueilAdmin;
import eu.telecomnancy.javafx.controller.AccueilEnseignant;
import eu.telecomnancy.javafx.controller.AccueilEtudiant;
import eu.telecomnancy.javafx.controller.Calendrier;
import eu.telecomnancy.javafx.controller.Connexion;
import eu.telecomnancy.javafx.controller.MenuBarController;
import eu.telecomnancy.javafx.controller.MenuBarConnexion;
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

    public AccesPages(ProfRDV profRDV){
        this.profRDV = profRDV;
    }
    
    public void accesAccueilConnexion(){
        Stage stage = profRDV.getStage();
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(getClass().getResource("/fxml/Connexion.fxml"));
        MenuBarConnexion menuBarConnexion = new MenuBarConnexion(profRDV);
        Connexion connexionController = new Connexion(profRDV);
        profRDV.setControlleur(connexionController);
        profRDV.reinitializeInstance();
        loader.setControllerFactory(ic -> {
        if (ic.equals(eu.telecomnancy.javafx.controller.Connexion.class)) return connexionController;
        if (ic.equals(eu.telecomnancy.javafx.controller.MenuBarConnexion.class)) return menuBarConnexion;
        else return null ;
        });
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 1000);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("exception chargement page AccueilConnexion - AccesAccueil");
            System.out.println(e.getMessage());
        }

    }


    public void accesAccueilEtudiant(){
        Stage stage = profRDV.getStage();
        FXMLLoader loader = new FXMLLoader(); 
        AccueilEtudiant accueilEtudiant = new AccueilEtudiant(profRDV);
        MenuBarController menuBar = new MenuBarController(profRDV);
        Calendrier calendrier = new Calendrier(profRDV);
        profRDV.setControlleur(accueilEtudiant);
        profRDV.setInstance("eleve");
        loader.setLocation(getClass().getResource("/fxml/AccueilEtudiant.fxml"));
        loader.setControllerFactory(ic -> {
        if (ic.equals(eu.telecomnancy.javafx.controller.MenuBarController.class)) return menuBar;
        if (ic.equals(eu.telecomnancy.javafx.controller.AccueilEtudiant.class)) return accueilEtudiant;
        if (ic.equals(eu.telecomnancy.javafx.controller.Calendrier.class)) return calendrier;
        else return null ;
        });
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 1000);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("exception chargement page AccueilEtudiant - AccesAccueil");
            System.out.println(e.getMessage());
        }

    }

    public void accesAccueilEnseignant(){
        Stage stage = profRDV.getStage();
        FXMLLoader loader = new FXMLLoader(); 
        AccueilEnseignant accueilEnseignant = new AccueilEnseignant(profRDV);
        MenuBarController menuBar = new MenuBarController(profRDV);
        Calendrier calendrier = new Calendrier(profRDV);
        profRDV.setControlleur(accueilEnseignant);
        profRDV.setInstance("prof");
        loader.setLocation(getClass().getResource("/fxml/AccueilEnseignant.fxml"));
        loader.setControllerFactory(ic -> {
        if (ic.equals(eu.telecomnancy.javafx.controller.MenuBarController.class)) return menuBar;
        if (ic.equals(eu.telecomnancy.javafx.controller.AccueilEnseignant.class)) return accueilEnseignant;
        if (ic.equals(eu.telecomnancy.javafx.controller.Calendrier.class)) return calendrier;
        else return null ;
        });
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 1000);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("exception chargement page AccueilEnseignant - AccesAccueil");
            System.out.println(e.getMessage());
        }

    }

    public void accesAccueilAdmin(){
        Stage stage = profRDV.getStage(); 
        FXMLLoader loader = new FXMLLoader(); 
        AccueilAdmin accueilAdmin = new AccueilAdmin(profRDV);
        MenuBarController menuBar = new MenuBarController(profRDV);
        profRDV.setControlleur(accueilAdmin);
        profRDV.setInstance("admin");
        loader.setLocation(getClass().getResource("/fxml/AccueilAdmin.fxml"));
        loader.setControllerFactory(ic -> {
        if (ic.equals(eu.telecomnancy.javafx.controller.MenuBarController.class)) return menuBar;
        if (ic.equals(eu.telecomnancy.javafx.controller.AccueilEtudiant.class)) return accueilAdmin;
        else return null ;
        });
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 1000);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("exception chargement page AccueilAdmin - AccesAccueil");
            System.out.println(e.getMessage());
        }

    }

}
