package eu.telecomnancy.javafx.model;

import java.util.ArrayList;

public class RDV {

    public Boolean status;
    public Boolean archive;

    public String lieu;
    public String libelle;
    public Enseignant enseignant;
    public Etudiant Etudiant;
    public Creneau creneau;


    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public void setEtudiants(Etudiant student) {
        this.Etudiant = student;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }

}
