package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AdminAjoutUtilisateur extends Controlleur{

    @FXML
    private RadioButton etudiant;

    @FXML
    private RadioButton enseignant;

    @FXML
    private TextField nom; 

    @FXML
    private TextField prenom; 

    @FXML
    private TextField sexe;

    @FXML
    private TextField mail;
    
    @FXML
    private TextField adresse; 

    @FXML
    private TextField telephone; 

    @FXML
    private Button valider; 

    @FXML
    private void initialize() {
        ToggleGroup group = new ToggleGroup();
        etudiant.setToggleGroup(group);
        enseignant.setToggleGroup(group);
    }

    public AdminAjoutUtilisateur(ProfRDV profRDV) {
        super(profRDV);
    }

    public void ajoutUser(){
        // if(etudiant.isSelected()){
        //     String table = "eleve";
        // }else{
        //     String table = "enseignant";
        // }

        // profRDV.modificationUsers.Add(nom.getText(), prenom.getText(), mail.getText(), sexe.getText(), date.getText(), address.g, tel, table);
    }

}
