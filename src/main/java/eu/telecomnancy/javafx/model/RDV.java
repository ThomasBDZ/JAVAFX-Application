package eu.telecomnancy.javafx.model;

import java.util.ArrayList;

public class RDV {

    public Boolean confirmed;
    public Boolean refused;
    public Boolean archived;

    public String lieu;
    public String libelle;
    public String Enseignant;
    public ArrayList<String> Etudiants;
    public Creneau creneau;

    public RDV(Boolean confirmed,Boolean refused,Boolean archived,String lieu,String libelle,String Enseignant, ArrayList<String> Etudiants,Creneau creneau){
        this.confirmed=confirmed;
        this.refused=refused;
        this.archived=archived;
        this.Enseignant=Enseignant;
        this.Etudiants=Etudiants;
        this.libelle=libelle;
        this.lieu=lieu;
        this.creneau=creneau;
    }

}
