package eu.telecomnancy.javafx.model;

import java.util.ArrayList;

public class RDV {

    public Boolean status;
    public Boolean archive;

    public String lieu;
    public String libelle;
    public Enseignant Enseignant;
    public ArrayList<Etudiant> Etudiants;
    public Creneau creneau;

    public RDV(Boolean status,Boolean archived,String lieu,String libelle,Enseignant Enseignant, ArrayList<Etudiant> Etudiants,Creneau creneau){
        this.status=status;
        this.archive=archived;
        this.Enseignant=Enseignant;
        this.Etudiants=Etudiants;
        this.libelle=libelle;
        this.lieu=lieu;
        this.creneau=creneau;
    }

}
