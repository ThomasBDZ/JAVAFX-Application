package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.GestionnaireDB.ModificationUsers;
import javafx.fxml.FXML;

public class AccueilAdmin extends Controlleur {



    public AccueilAdmin(ProfRDV profRDV){
        super(profRDV);
    }

    public void addUser(){
        profRDV.getAccesPages().AdminAjoutUtilisateur();
    }

    public void modifUser(){
        profRDV.getAccesPages().AdminModifierUtilisateur();
    }

    public void delUser(){
        
    }

    public void showArchive(){
        profRDV.getAccesPages().AdminArchive();
    }
 
}
