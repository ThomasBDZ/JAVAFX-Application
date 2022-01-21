package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;

import java.util.ArrayList;
import java.util.Collection;

import eu.telecomnancy.javafx.controller.Erreurs.InsertionException;
import eu.telecomnancy.javafx.model.Enseignant;
import eu.telecomnancy.javafx.model.Etudiant;
import eu.telecomnancy.javafx.model.Utilisateur;
import eu.telecomnancy.javafx.model.GestionnaireDB.GetterUser;
import eu.telecomnancy.javafx.model.GestionnaireDB.PickUser;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class AdminModifierUtilisateur extends Controlleur{

    private Label erreur;
    private Boolean erreurShown;

    @FXML
    private RadioButton etudiant;

    @FXML
    private RadioButton enseignant;

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
    private VBox vbox;

    @FXML
    private ListView<Label> listeView;

    @FXML
    private void initialize() {
        ToggleGroup group = new ToggleGroup();
        etudiant.setToggleGroup(group);
        enseignant.setToggleGroup(group);
    }


    public AdminModifierUtilisateur(ProfRDV profRDV){
        super(profRDV);
        this.erreurShown = false;
    }

    public void chargerListeEtudiants(){

        ArrayList<Label> labels = new ArrayList<Label>() ;
        ArrayList<Etudiant> liste = GetterUser.getAllEleves();
        for(Etudiant eleve : liste){
            String nomStr = eleve.nom;
            String prenomStr = eleve.prenom;
            String mailStr = eleve.mail;

            Label label = new Label(prenomStr + " " + nomStr + " (" + mailStr +") ");
            label.setId(mailStr);
            label.setOnMouseClicked(event ->{
                mail.setText(mailStr);
            });

            labels.add(label);

        }

        ObservableList<Label> items = FXCollections.observableArrayList(labels);
        listeView.setItems(items);



    }


    public void chargerListeEnseignants(){

        ArrayList<Label> labels = new ArrayList<Label>() ;
        ArrayList<Enseignant> liste = GetterUser.getAllProfs();
        for(Enseignant enseignant : liste){
            String nomStr = enseignant.nom;
            String prenomStr = enseignant.prenom;
            String mailStr = enseignant.mail;

            Label label = new Label(prenomStr + " " + nomStr + " (" + mailStr +") ");
            label.setId(mailStr);
            label.setOnMouseClicked(event ->{
                mail.setText(mailStr);
            });

            labels.add(label);

        }

        ObservableList<Label> items = FXCollections.observableArrayList(labels);
        listeView.setItems(items);

    }


    public void modifUser(){
        Utilisateur user;
        if(etudiant.isSelected()){
            user = GetterUser.getInfoEleve(PickUser.Pick(mail.getText()));
        }
        else{
            user = GetterUser.getInfoProf(PickUser.Pick(mail.getText()));
        }
        
        if(adresse.getText()!= null || adresse.getText()!= ""  ){
            try {
                profRDV.modificationUsers.update(user, adresse.getText(), "adresse");
            } catch (InsertionException e) {
                if(erreurShown){
                    vbox.getChildren().remove(erreur);
                }
                this.erreur  = ((InsertionException) e).getError();
                this.erreurShown = true;
                vbox.getChildren().add(erreur);
            }
            
        }

        if(telephone.getText()!= null || telephone.getText()!= ""  ){
            try {
                profRDV.modificationUsers.update(user, telephone.getText(), "telephone");
            } catch (Exception e) {
                if(erreurShown){
                    vbox.getChildren().remove(erreur);
                }
                this.erreur  = ((InsertionException) e).getError();
                this.erreurShown = true;
                vbox.getChildren().add(erreur);
            }
        }
        profRDV.getAccesPages().accesAccueilAdmin();
        
    }
    
}
