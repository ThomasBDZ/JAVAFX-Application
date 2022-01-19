package eu.telecomnancy.javafx.model.GestionnaireDB;

public class Creneau {

    public Creneau(){

    }
    private int id;
    private int id_prof;
    private int indice;
    private String date;

    public Creneau(int id, int id_prof, int indice, String date){
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
    public void setDate(String date){
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
    public String getDate(){
        return this.date;
    }
}
