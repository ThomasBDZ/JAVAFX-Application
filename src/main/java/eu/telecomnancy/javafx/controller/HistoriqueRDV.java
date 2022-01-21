package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.RDV;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import eu.telecomnancy.javafx.model.GestionnaireDB.changeStatus;
import eu.telecomnancy.javafx.model.Creneau;
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

public class HistoriqueRDV extends Controlleur{

    @FXML
    private ListView listeView;

    private ArrayList<RDV> liste;

    public HistoriqueRDV(ProfRDV profRDV) {
        super(profRDV);
    }


    @FXML
    public void initialize(){
        chargerListe();
    }

    public void chargerListe(){ 
        Utilisateur user = profRDV.utilisateur;
        ArrayList<Label> labels = new ArrayList<Label>() ;
        try {
            liste = GetterRdv.getAllRDV(user);
            for(RDV rdv : liste){
                System.out.println(rdv.lieu + " " + rdv.libelle);
            }
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
            String statut = "RDV en attente de validation!";
            if(rdv.status){ 
                statut= "RDV Validé";
                if(rdv.archive){
                    statut= "RDV passé et archivé";
                }
                else{
                    statut= "RDV à venir ou passé";
                }
            } else{
                if(rdv.archive){statut = "RDV supprimé ";}
            }
            

            Label label = new Label(date  +" -  Libellé: "+libelle + " Lieu:  " + lieu +
              "\n Étudiant:  " +nomEtudiant + " " + prenomEtudiant + " -  mail:  " + mailEtudiant + 
              "\n " + statut
              );
    
            label.setId(Integer.toString(changeStatus.getIdRdv(rdv)));
            label.setOnMouseClicked(event ->{
                profRDV.getAccesPages().accesDescriptionRDV(rdv);
            });
            
            labels.add(label);
            

        }
        ObservableList<Label> items = FXCollections.observableArrayList(labels);
        listeView.setItems(items);
        

    }

}
