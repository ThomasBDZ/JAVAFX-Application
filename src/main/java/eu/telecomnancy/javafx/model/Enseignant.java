package eu.telecomnancy.javafx.model;

import java.util.ArrayList;

public class Enseignant extends Utilisateur{

    ArrayList<Creneau> listeCreneaux = new ArrayList<Creneau>();

    public Enseignant(String nom, String prenom, String mail, String sexe, String telephone, String addresse,
            String birthDate) {
        super(nom, prenom, mail, sexe, telephone, addresse, birthDate);
        //TODO Auto-generated constructor stub
    }

    public Enseignant(){
        super();
    }
   
    

}
