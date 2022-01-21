package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.RDV;
import eu.telecomnancy.javafx.model.utils.DateConversion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DescriptionRDV extends Controlleur implements Initializable {

    private RDV rdv;

    public DescriptionRDV(ProfRDV profRDV){
        super(profRDV);
    }

    public DescriptionRDV(ProfRDV profRDV, RDV rdv) {
        super(profRDV);
        this.rdv = rdv;
    }

    @FXML
    public Label labelDate;

    @FXML
    public Label labelHeure;

    @FXML
    public Label labelEnseignant;

    @FXML
    public Label labelLieu;

    @FXML
    public Label labelLibelle;

    @FXML
    public TextField fieldDescription;

    @FXML
    public Label labelStatut;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
           labelDate.setText(DateConversion.dateToString(rdv.creneau.date));
            labelLieu.setText(rdv.lieu);
            labelHeure.setText(rdv.creneau.getHeure());
            labelLibelle.setText(rdv.libelle);
            fieldDescription.setText(rdv.description);
           labelEnseignant.setText(rdv.enseignant.prenom+ " "+rdv.enseignant.nom );
            boolean s= rdv.status;
             if(s){
                 labelStatut.setText("Confirmé");
            }
             else{
                if(rdv.archive){
                    labelStatut.setText("Refusé");
                }
                else{
                    labelStatut.setText("En attente de confirmation");
                }
            }
    }
}
