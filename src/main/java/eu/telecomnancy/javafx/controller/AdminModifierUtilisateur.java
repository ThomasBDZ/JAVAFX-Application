package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.controller.Erreurs.InsertionException;
import eu.telecomnancy.javafx.model.Enseignant;
import eu.telecomnancy.javafx.model.Etudiant;
import eu.telecomnancy.javafx.model.Utilisateur;
import eu.telecomnancy.javafx.model.GestionnaireDB.GetterUser;
import eu.telecomnancy.javafx.model.GestionnaireDB.PickUser;
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
    }

    public void chargerListeEtudiants(){

    // Utilisateur user = profRDV.utilisateur;

    // GetterUser.getInfoEleve(PickUser.Pick(user.mail));
    
    // ObservableList<Label> items =FXCollections.observableArrayList (
    //     "Single", "Double", "Suite", "Family App");
    // listeView.setItems(items);



    }


    public void chargerListeEnseignants(){

    }


    public void modifUser(){

    }
    
}
