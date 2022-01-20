package eu.telecomnancy.javafx.model;

import java.util.Date;

public class Creneau {

    public Creneau(){

    }
    private int id;
    private int id_prof;
    private int indice;
    private java.util.Date date;

    public Creneau(int indice, Date date){
        this.indice=indice;
        this.date=date;

    }

    public Creneau(int id, int id_prof, int indice, java.util.Date date){
        this.date = date;
        this.id = id;
        this.id_prof = id_prof;
        this.indice = indice;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setId_prof(int id_prof){
        this.id_prof = id_prof;
    }
    public void setIndice(int indice){
        this.indice = indice;
    }
    public void setDate(java.util.Date date){
        this.date = date;
    }

    public int getId(){
        return this.id;
    }
    public int getId_prof(){
        return this.id_prof;
    }
    public int getIndice(){
        return this.indice;
    }
    public java.util.Date getDate(){
        return this.date;
    }

    String getHeure(){
        int heure= indice/3+HEURE_MIN;
        int minutes=(indice)%3*20;
        return heure+"h"+minutes;
    }


}
