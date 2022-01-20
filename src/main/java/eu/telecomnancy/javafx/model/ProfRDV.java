package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.controller.Controlleur;
import eu.telecomnancy.javafx.controller.utils.AccesPages;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
     * Modèle principal pour notre application ProfRDV
     */
public class ProfRDV {

    AccesPages accesPages;


    public String utilisateur; //mail de l'utilisateur courant
    public GestionnaireRDV gestionnaireRDV;
    public GestionnaireCreneauDispo gestionnaireCreneauDispo;
    String instance = ""; // Soit "eleve", "prof", "admin", soit ""
    Stage stage;

    public ProfRDV(){

        this.accesPages = new AccesPages(this);
        this.gestionnaireCreneauDispo= new GestionnaireCreneauDispo();
        this.gestionnaireRDV = new GestionnaireRDV();

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
