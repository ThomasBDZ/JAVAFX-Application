package eu.telecomnancy.javafx;

import eu.telecomnancy.javafx.controller.Connexion;
import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    // @Override
    // public void start(Stage stage) throws Exception {
    //     Parent root = FXMLLoader.load(getClass().getResource("/fxml/scene.fxml"));

    //     Scene scene = new Scene(root);
    //     scene.getStylesheets().add(getClass().getResource("/stylesheet/style.css").toExternalForm());

    //     stage.setTitle("JavaFX and Gradle");
    //     stage.setScene(scene);
    //     stage.show();
    // }
    @Override
    public void start(Stage primaryStage) throws Exception {

        ProfRDV profRDV = new ProfRDV();
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(getClass().getResource("/fxml/Principale.fxml"));
        Connexion connexionController = new Connexion(profRDV);
        loader.setControllerFactory(ic -> {
        if (ic.equals(eu.telecomnancy.javafx.controller.Connexion.class)) return connexionController;
        else return null ;
        });
        Parent root = loader.load() ;
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }

}