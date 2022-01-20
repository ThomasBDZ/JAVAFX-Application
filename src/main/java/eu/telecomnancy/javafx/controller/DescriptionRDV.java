package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.RDV;

public class DescriptionRDV extends Controlleur{

    RDV rdv;
    
    public DescriptionRDV(ProfRDV profRDV, RDV rdv) {
        super(profRDV);
        this.rdv = rdv;
    }


}
