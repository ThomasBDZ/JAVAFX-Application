package eu.telecomnancy.javafx.model;


import java.util.Date;

import static eu.telecomnancy.javafx.model.utils.GlobalVariables.*;


public class Creneau {
    public int indice;
    public Date date;


    public Creneau(int indice, Date date ){
        this.indice=indice;
        this.date=date;

    }

    String getHeure(){
        int heure= indice/3+HEURE_MIN;
        int minutes=(indice)%3*20;
        return heure+"h"+minutes;
    }


}
