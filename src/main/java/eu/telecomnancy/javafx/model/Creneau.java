package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.model.utils.GlobalVariables;

import java.util.Date;

import eu.telecomnancy.javafx.model.utils.GlobalVariables;


public class Creneau {

    public Date date;
    public int id;
    public int id_prof;
    public int indice;

    public Creneau(){

    }

    public Creneau(int indice, Date date, int id_prof){
        this.indice=indice;
        this.date=date;
        this.id_prof=id_prof;

    }

    public Creneau(int id, int id_prof, int indice, java.util.Date date){
        this.date = date;
        this.id = id;
        this.id_prof = id_prof;
        this.indice = indice;
    }


    public String getHeure(){
        int heure= indice/3+ GlobalVariables.HEURE_MIN;
        int minutes=(indice)%3*20;
        if(minutes==0){
            return heure+"h"+minutes+"0";
        }
        return heure+"h"+minutes;
    }


}
