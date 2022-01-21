package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RecapRDV extends Controlleur implements Initializable {

    private RDV rdv;

    public Boolean status;
    public Boolean archive;
    public String lieu;
    public String libelle;

    public RecapRDV(ProfRDV profRDV){
        super(profRDV);
    }

    public RecapRDV(ProfRDV profRDV, RDV rdv) {
        super(profRDV);
        this.rdv=rdv;
    }

    @FXML
    public Label labelDate;

    @FXML
    public Label labelHeure;

    @FXML
    public Label labelEnseignant;

    @FXML
    public TextField fieldLieu;

    @FXML
    public TextField  fieldDescription;

    @FXML
    public TextField  fieldLibelle;

    @FXML
    public Button valider;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
