package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.controller.Controlleur;
import eu.telecomnancy.javafx.controller.utils.AccesPages;
import eu.telecomnancy.javafx.model.GestionnaireDB.*;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
     * Modèle principal pour notre application ProfRDV
     */
public class ProfRDV {

    AccesPages accesPages;

    public Utilisateur utilisateur; 
    public GestionRDV gestionnaireRDV;
    public GestionnaireCreneauDispo gestionnaireCreneauDispo;
    public DisponibilityProf disponibilityProf;
    String instance = ""; // Soit "eleve", "prof", "admin", soit ""
    Stage stage;

    //GestionnaireDB
    public ModificationUsers modificationUsers;

    public ProfRDV(){

        this.accesPages = new AccesPages(this);
        this.gestionnaireCreneauDispo= new GestionnaireCreneauDispo();
        this.gestionnaireRDV = new GestionRDV();
        this.modificationUsers = new ModificationUsers();
        this.disponibilityProf=new DisponibilityProf();
    }

    /**
     * Setter for instance (eleve,admin,prof)
     * @param instance
     * @throws Exception
     */
    public void setInstance(String instance){
        switch (instance) {
            case "eleve":
                this.instance = "eleve";
                break;

            case "prof":
                this.instance = "prof";
                break;

            case "admin":
                this.instance = "admin";
                break;

            default:
                System.out.println("Mauvais type d'instance ('eleve','prof','admin')");
                
        }

    }

    /**
     * Getter for the instance (eleve,admin,prof)
     * @return instance 
     */
    public String getInstance(){
        return this.instance;
    }

    /**
     * Reinitialize the instance to ""
     */
    public void reinitializeInstance(){
        this.instance="";
    }


    /**
     * Getter for AccesPages class.
     * @return the accesPages
     */
    public AccesPages getAccesPages() {
        return accesPages;
    }

    /**
     * Setter stage
     * @param stage
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    /**
     * Getter for stage
     * @return Stage
     */
    public Stage getStage(){
        return this.stage;
    }


    
}
