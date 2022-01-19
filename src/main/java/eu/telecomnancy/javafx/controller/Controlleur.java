package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;

public abstract class Controlleur {

    protected ProfRDV profRDV;

    public Controlleur(ProfRDV profRDV){
        this.profRDV = profRDV;
    }
    
}
