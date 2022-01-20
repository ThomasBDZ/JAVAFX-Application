package eu.telecomnancy.javafx.model;

import java.util.ArrayList;
import java.util.Date;

public abstract class Utilisateur {
    public String nom;
    public String prenom;
    public String mail;
    public String telephone;
    public String addresse;
    public String sexe;
    public Date birthDate;
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

    public void setRdv(ArrayList<RDV> rdv) {
        this.rdv = rdv;
    }

    public void setBirthDate(Date birthDate) { this.birthDate = birthDate;}
}
