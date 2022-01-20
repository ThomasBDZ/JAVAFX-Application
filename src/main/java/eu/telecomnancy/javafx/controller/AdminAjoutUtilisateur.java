package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.Enseignant;
import eu.telecomnancy.javafx.model.Etudiant;
import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    private DatePicker date;

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
        try {
            Utilisateur user;
            if(etudiant.isSelected()){
                user = new Etudiant(nom.getText(), prenom.getText(), mail.getText(), sexe.getText(), telephone.getText(), adresse.getText(), date.getValue().toString());   
            } else{
                user = new Enseignant(nom.getText(), prenom.getText(), mail.getText(), sexe.getText(), telephone.getText(), adresse.getText(), date.getValue().toString());   
            }
            profRDV.modificationUsers.Add(user);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    
        profRDV.getAccesPages().accesAccueilAdmin();
    }
    
}
