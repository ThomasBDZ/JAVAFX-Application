package eu.telecomnancy.javafx;

import eu.telecomnancy.javafx.controller.AccueilEnseignant;
import eu.telecomnancy.javafx.controller.AccueilEtudiant;
import eu.telecomnancy.javafx.controller.Calendrier;
import eu.telecomnancy.javafx.controller.Connexion;
import eu.telecomnancy.javafx.controller.MenuBarConnexion;
import eu.telecomnancy.javafx.controller.MenuBarController;
import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ProfRDV profRDV = new ProfRDV();
        profRDV.setStage(primaryStage);
        profRDV.getAccesPages().accesAccueilConnexion();
    }
    

    public static void main(String[] args) {
        launch(args);
    }

}