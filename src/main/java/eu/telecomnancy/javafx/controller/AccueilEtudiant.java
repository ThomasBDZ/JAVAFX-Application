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

public class AccueilEtudiant {

    private ProfRDV profRDV;

    public AccueilEtudiant(ProfRDV profRDV){
        this.profRDV = profRDV;
    }
    
}