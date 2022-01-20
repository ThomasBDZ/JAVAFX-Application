package eu.telecomnancy.javafx.controller;

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
import java.time.temporal.WeekFields;

public class AccueilEtudiant extends Controlleur {

    public AccueilEtudiant(ProfRDV profRDV) {
        super(profRDV);
    }

    @FXML
    public void Historique(){}

    @FXML
    public void ajouterRDV(){

    }

    @FXML
    public void prochain(){}

    @FXML
    public void dernier(){

    }

}
