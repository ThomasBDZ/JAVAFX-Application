package eu.telecomnancy.javafx.controller.Erreurs;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class MauvaisChamp extends Exception {

    private Label erreur;

    public MauvaisChamp(String info){
        super();
        this.erreur = new Label(info);
        this.erreur.setTextFill(Color.RED);
    }

    public Label getError() {
       return this.erreur;
    }
    
}
