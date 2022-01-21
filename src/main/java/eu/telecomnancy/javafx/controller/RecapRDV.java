package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.RDV;

public class RecapRDV extends Controlleur{
    private RDV rdv;
    public RecapRDV(ProfRDV profRDV){
        super(profRDV);
    }

    public RecapRDV(ProfRDV profRDV, RDV rdv) {
        super(profRDV);
        this.rdv=rdv;
    }
}
