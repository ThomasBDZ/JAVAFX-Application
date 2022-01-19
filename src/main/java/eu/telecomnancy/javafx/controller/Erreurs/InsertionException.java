package eu.telecomnancy.javafx.controller.Erreurs;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class InsertionException extends Exception{

    private Label erreur;

    public InsertionException(String typeInvalide){
        super();
        this.erreur = new Label(String.format("%s invalide ! Réessayer.", typeInvalide));
        this.erreur.setTextFill(Color.RED);
    }

    public InsertionException(String typeInvalide, String specificationType){
        super();
        this.erreur = new Label(String.format("%s invalide ! Réessayer. %s", typeInvalide,specificationType));
        this.erreur.setTextFill(Color.RED);
    }
    public InsertionException(String table, String nom, String prenom){
        super();
        this.erreur = new Label(String.format("%s's user already in data base ( %s %s )",table,prenom,nom));
        this.erreur.setTextFill(Color.RED);
    }

    public Label getError() {
       return this.erreur;
    }
    
}

