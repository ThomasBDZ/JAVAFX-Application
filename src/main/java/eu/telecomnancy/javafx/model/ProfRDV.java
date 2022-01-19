package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.controller.Controlleur;
import eu.telecomnancy.javafx.controller.utils.AccesPages;

/**
     * Modèle principal pour notre application ProfRDV
     */
public class ProfRDV {

    AccesPages accesPages;
    /**
     * String instance 
     */
    String instance = ""; // Soit "eleve", "prof", "admin", soit ""
    Controlleur currentControlleur;

    public ProfRDV(){
        this.accesPages = new AccesPages(this);
    }

    /**
     * Setter for currentControlleur
     * @param controlleur
     */
    public void setControlleur(Controlleur controlleur){
        this.currentControlleur = controlleur;
    }

    /**
     * Getter for currentControlleur
     * @return the currentControlleur
     */
    public Controlleur getCurrentControlleur(){
        return currentControlleur;
    }


    /**
     * Getter for AccesPages class.
     * @return the accesPages
     */
    public AccesPages getAccesPages() {
        return accesPages;
    }

    
    
    
}
