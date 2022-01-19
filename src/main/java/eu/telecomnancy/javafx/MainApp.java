package eu.telecomnancy.javafx;

import eu.telecomnancy.javafx.controller.AccueilEnseignant;
import eu.telecomnancy.javafx.controller.AccueilEtudiant;
import eu.telecomnancy.javafx.controller.Calendrier;
import eu.telecomnancy.javafx.controller.Connexion;
import eu.telecomnancy.javafx.controller.MenuBar;
import eu.telecomnancy.javafx.controller.MenuBarConnexion;
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
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(getClass().getResource("/fxml/Connexion.fxml"));
        MenuBarConnexion menuBarConnexion = new MenuBarConnexion(profRDV);
        Connexion connexionController = new Connexion(profRDV);
        profRDV.setControlleur(connexionController);
        loader.setControllerFactory(ic -> {
        if (ic.equals(eu.telecomnancy.javafx.controller.Connexion.class)) return connexionController;
        if (ic.equals(eu.telecomnancy.javafx.controller.MenuBarConnexion.class)) return menuBarConnexion;
        else return null ;
        });
        Parent root = loader.load() ;
        // scene.getStylesheets().add(getClass().getResource("/stylesheet/style.css").toExternalForm());
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }

}