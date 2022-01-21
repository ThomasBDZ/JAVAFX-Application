package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.controller.Erreurs.MauvaisChamp;
import eu.telecomnancy.javafx.model.Etudiant;
import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.Utilisateur;
import eu.telecomnancy.javafx.model.GestionnaireDB.GetterUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

public class UtilisateurModifierMdp extends Controlleur{

    private Label erreur;
    private Boolean erreurShown = false;

    @FXML
    private PasswordField oldMdp;

    @FXML
    private PasswordField newMdp1;

    @FXML
    private PasswordField newMdp2;

    @FXML
    private VBox vboxMdp;

    public UtilisateurModifierMdp(ProfRDV profRDV){
        super(profRDV);
    }


    public void changementMdp(){
        Utilisateur user = profRDV.utilisateur;
        String mdpCompare = GetterUser.getMdp(user);
        if(!mdpCompare.equals(oldMdp.getText())){
            if(erreurShown){
                vboxMdp.getChildren().remove(erreur);
            }
            this.erreur  = (new MauvaisChamp("Le mot de passe n'est pas bon ! ")).getError();
            this.erreurShown = true;
            vboxMdp.getChildren().add(erreur);
        } 
        
        else if(! newMdp1.getText().equals(newMdp2.getText())){
            if(erreurShown){
                vboxMdp.getChildren().remove(erreur);
            }
            this.erreur  = (new MauvaisChamp("Les mots de passes ne correspondent pas ! ")).getError();
            this.erreurShown = true;
            vboxMdp.getChildren().add(erreur);
        }
        

        else{
            try{
                profRDV.modificationUsers.modifMdp(user,newMdp1.getText());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            
            if(user instanceof Etudiant){
                profRDV.getAccesPages().accesAccueilEtudiant();
            } else{
                profRDV.getAccesPages().accesAccueilEnseignant();
            }
            
        }

    }

    

    




    
}
