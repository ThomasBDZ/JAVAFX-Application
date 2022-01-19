package eu.telecomnancy.javafx.model;


import eu.telecomnancy.javafx.controller.AccueilEnseignant;
import eu.telecomnancy.javafx.controller.AccueilEtudiant;
import eu.telecomnancy.javafx.controller.Calendrier;
import eu.telecomnancy.javafx.controller.Connexion;
import eu.telecomnancy.javafx.controller.MenuBarPrincipal;
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
    


    public void accesAccueil(Node node, String path){
        Stage stage =(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(); 
        AccueilEnseignant accueilEnseignant = new AccueilEnseignant(profRDV);
        AccueilEtudiant accueilEtudiant = new AccueilEtudiant(profRDV);
        MenuBarPrincipal menuBarPrincipal = new MenuBarPrincipal(profRDV);
        Connexion connexionController = new Connexion(profRDV);
        Calendrier calendrier = new Calendrier(profRDV);
        loader.setLocation(getClass().getResource(path));
        loader.setControllerFactory(ic -> {
        if (ic.equals(eu.telecomnancy.javafx.controller.Connexion.class)) return connexionController;
        if (ic.equals(eu.telecomnancy.javafx.controller.MenuBarPrincipal.class)) return menuBarPrincipal;
        if (ic.equals(eu.telecomnancy.javafx.controller.AccueilEtudiant.class)) return accueilEtudiant;
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
            System.out.println("exception ---- ");
            //TODO: handle exception
        }

    }

}
