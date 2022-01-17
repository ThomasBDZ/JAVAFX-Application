package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;

public class MenuBarPrincipal {
    
    /**
     * Controleur de la vue MenuBar.fxml
     */

    ProfRDV profRDV;

    public MenuBarPrincipal(ProfRDV profRDV){
        this.profRDV = profRDV;
    }
   
    /**
     * Controle du bouton de deconnexion dans le menu bar principal.
     * Si l'utilisateur est connecté.
     * Non affiché si on est pas connecté.
     */
    public void deconnexion(){

    }

    /**
     * Controle du bouton accueil dans le menu bar principale. 
     * --> Renvoit vers la page d'accueil si connecté.
     * --> Ne fait rien sinon.
     */
    public void accueil(){

    }

    /**
     * Controle du bouton quitter dans le menu bar principale. 
     * --> Quitte la page.
     */
    public void quitter(){
        //TODO: ajout de la demande de confirmation avant de quitter

        
    }
    
    
}
