package eu.telecomnancy.javafx.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import eu.telecomnancy.javafx.model.Creneau;
import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.RDV;
import eu.telecomnancy.javafx.model.Utilisateur;
import eu.telecomnancy.javafx.model.GestionnaireDB.GestionRDV;
import eu.telecomnancy.javafx.model.GestionnaireDB.GetterRdv;
import eu.telecomnancy.javafx.model.utils.DateConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Demandes extends Controlleur{

    @FXML
    private VBox listeRDV;

    @FXML
    private VBox vbox;

    @FXML
    private TextArea raisonRefus;

    @FXML
    public void initialize(){
        chargerListe();
    }

    public Demandes(ProfRDV profRDV){
        super(profRDV);
    }

    public void confirmer(){

    }

    public void refuser(){

    }

    public void chargerListe(){ 
        ArrayList<RDV> liste = new ArrayList<RDV>();
        Utilisateur user = profRDV.utilisateur;
        ArrayList<Label> labels = new ArrayList<Label>() ;
        try {
            liste = GetterRdv.getAllRdvNonValide(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // ArrayList<RDV> listeOrdonnee = profRDV.gestionnaireRDV.
        // TODO:

        for(RDV rdv : liste){
            String libelle = rdv.libelle;
            String nomEtudiant = rdv.etudiant.nom;
            String prenomEtudiant = rdv.etudiant.prenom;
            String mailEtudiant = rdv.etudiant.mail;
            String description = rdv.description;
            Creneau creneau = rdv.creneau;
            String lieu = rdv.lieu;
            String date = DateConversion.dateToString(rdv.creneau.date);

            Label label = new Label(date  +" -  Libellé: "+libelle + " Lieu:  " + lieu +  "\n Étudiant:  " +nomEtudiant + " " + prenomEtudiant + " -  mail:  " + mailEtudiant);
            label.setId(mailEtudiant);
            label.setOnMouseClicked(event ->{
                profRDV.getAccesPages().accesDescriptionRDV(rdv);
            });
            
            labels.add(label);
            

        }
        ListView<Label> listeView = new ListView<Label>();
        ObservableList<Label> items = FXCollections.observableArrayList(labels);
        listeView.setItems(items);
        vbox.getChildren().add(listeView);
        

        


    }

}
