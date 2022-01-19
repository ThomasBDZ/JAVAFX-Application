package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.controller.Controlleur;
import eu.telecomnancy.javafx.controller.utils.AccesPages;

/**
     * Modèle principal pour notre application ProfRDV
     */
public class ProfRDV {

    AccesPages accesPages;
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
     * Setter for instance (eleve,admin,prof)
     * @param instance
     * @throws Exception
     */
    public void setInstance(String instance) throws Exception{
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
                throw new Exception("Mauvais type d'instance ('eleve','prof','admin')");
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

    
    
    
}
