package eu.telecomnancy.javafx.controller.Erreurs;

public class ConnexionError extends Exception{

    private String erreur;

    public ConnexionError(String erreur){
        super();
        this.erreur = erreur;
    }

    public String getError() {
       return this.erreur;
    }



    
}
