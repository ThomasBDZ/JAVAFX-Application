package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.controller.utils.AccesPages;

/**
     * Modèle principal pour notre application ProfRDV
     */
public class ProfRDV {

    AccesPages accesPages;

    public String utilisateur; //mail de l'utilisateur courant
    public GestionnaireRDV gestionnaireRDV;
    public GestionnaireCreneauDispo gestionnaireCreneauDispo;

    public ProfRDV(){

        this.accesPages = new AccesPages(this);
        this.gestionnaireCreneauDispo= new GestionnaireCreneauDispo();
        this.gestionnaireRDV = new GestionnaireRDV();
    }


    /**
     * Getter for AccesPages class.
     * @return the accesPages
     */
    public AccesPages getAccesPages() {
        return accesPages;
    }

    
    
    
}
