package eu.telecomnancy.javafx;

import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.application.Application;
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