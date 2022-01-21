package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.*;
import eu.telecomnancy.javafx.model.utils.DateConversion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RecapRDV extends Controlleur implements Initializable {

    private RDV rdv;

    public Boolean status;
    public Boolean archived;
    public String lieu;
    public String libelle;
    public String description;

    public RecapRDV(ProfRDV profRDV){
        super(profRDV);
    }

    public RecapRDV(ProfRDV profRDV, RDV rdv) {
        super(profRDV);
        this.rdv=rdv;
        status=false;
        archived=false;

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
    public TextArea fieldDescription;

    @FXML
    public TextField  fieldLibelle;

    @FXML
    public Button valider;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            labelDate.setText(DateConversion.dateToString(rdv.creneau.date));

            labelEnseignant.setText(rdv.enseignant.prenom+" "+rdv.enseignant.nom);

            labelHeure.setText(rdv.creneau.getHeure());

            valider.setOnAction(event -> {
                lieu=fieldLieu.getText();
                description=fieldDescription.getText();
                libelle=fieldLibelle.getText();
                rdv.setRDV(status,archived,lieu,libelle,description);
                try {
                    profRDV.gestionnaireRDV.AddRdv(rdv);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("failed to add RDV");
                }
                profRDV.getAccesPages().accesAccueilEtudiant();
            });
    }
}
