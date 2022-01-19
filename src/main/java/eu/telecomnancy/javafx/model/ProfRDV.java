package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.controller.utils.AccesPages;

/**
     * Modèle principal pour notre application ProfRDV
     */
public class ProfRDV {

    AccesPages accesPages;
    
    public ProfRDV(){
        this.accesPages = new AccesPages(this);
    }


    /**
     * Getter for AccesPages class.
     * @return the accesPages
     */
    public AccesPages getAccesPages() {
        return accesPages;
    }

    
    
    
}
