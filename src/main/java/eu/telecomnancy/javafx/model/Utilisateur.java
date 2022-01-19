package eu.telecomnancy.javafx.model;

import java.util.ArrayList;

public abstract class Utilisateur {
    public String nom;
    public String prenom;
    public String mail;
    public String telephone;
    public String addresse;
    public String sexe;
    public Boolean etudiant;
    public ArrayList<RDV> rdv;

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public void setEtudiant(Boolean etudiant) {
        this.etudiant = etudiant;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
