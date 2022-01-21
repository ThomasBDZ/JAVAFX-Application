package eu.telecomnancy.javafx.model;

import java.util.ArrayList;

public class RDV {

    public Boolean status;
    public Boolean archive;

    public String lieu;
    public String libelle;
    public Enseignant enseignant;
    public Etudiant etudiant;
    public Creneau creneau;

    public RDV(){

    }

    public RDV(Boolean status,Boolean archived,String lieu,String libelle,Enseignant enseignant, Etudiant etudiant,Creneau creneau){
        this.status=status;
        this.archive=archived;
        this.enseignant=enseignant;
        this.etudiant=etudiant;
        this.libelle=libelle;
        this.lieu=lieu;
        this.creneau=creneau;

    }

    public RDV(Enseignant enseignant, Etudiant etudiant,Creneau creneau){
        this.Enseignant=Enseignant;
        this.Etudiants=Etudiants;
        this.creneau=creneau;
    }

}
