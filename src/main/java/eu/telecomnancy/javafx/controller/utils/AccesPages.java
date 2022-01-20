package eu.telecomnancy.javafx.controller.utils;


import eu.telecomnancy.javafx.controller.*;
import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.RDV;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class AccesPages {

    private ProfRDV profRDV;
    private MenuBarConnexion menuBarConnexion;
    private Connexion connexionController;
    private AccueilEtudiant accueilEtudiant;
    private MenuBarController menuBarController;
    private Calendrier calendrier;
    private AccueilEnseignant accueilEnseignant;
    
    

    //ADMIN
    private AccueilAdmin accueilAdmin;
    private ChangementMDP changementMDP;
    private Demandes demandes;
    private DescriptionRDV descriptionRDV;
    private Disponibilites disponibilites;
    private HistoriqueRDV historiqueRDV;
    private PriseRDV priseRDV;
    private RecapRDV recapRDV;


    private AdminAjoutUtilisateur adminAjoutUtilisateur;
    private AdminModifierUtilisateur adminModifierUtilisateur;
    private AdminArchive adminArchive;

    public AccesPages(ProfRDV profRDV){
        this.profRDV = profRDV;
        menuBarConnexion = new MenuBarConnexion(profRDV);
        connexionController = new Connexion(profRDV);
        accueilEtudiant = new AccueilEtudiant(profRDV);
        menuBarController = new MenuBarController(profRDV);
        calendrier = new Calendrier(profRDV);
        accueilEnseignant = new AccueilEnseignant(profRDV);
        accueilAdmin = new AccueilAdmin(profRDV);
        changementMDP = new ChangementMDP(profRDV);
        demandes = new Demandes(profRDV);
        disponibilites = new Disponibilites(profRDV);
        historiqueRDV = new HistoriqueRDV(profRDV);
        priseRDV = new PriseRDV(profRDV);
        recapRDV = new RecapRDV(profRDV);
        adminAjoutUtilisateur = new AdminAjoutUtilisateur(profRDV);
        adminModifierUtilisateur = new AdminModifierUtilisateur(profRDV);
        adminArchive = new AdminArchive(profRDV);
    }

    public void loadPage(String path){
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(getClass().getResource(path));
        Stage stage = profRDV.getStage();
        loader.setControllerFactory(ic -> {
        if (ic.equals(eu.telecomnancy.javafx.controller.Connexion.class)) return connexionController;
        if (ic.equals(eu.telecomnancy.javafx.controller.MenuBarConnexion.class)) return menuBarConnexion;
        if (ic.equals(eu.telecomnancy.javafx.controller.MenuBarController.class)) return menuBarController;
        if (ic.equals(eu.telecomnancy.javafx.controller.AccueilEtudiant.class)) return accueilEtudiant;
        if (ic.equals(eu.telecomnancy.javafx.controller.Calendrier.class)) return calendrier;
        if (ic.equals(eu.telecomnancy.javafx.controller.AccueilEnseignant.class)) return accueilEnseignant;
        if (ic.equals(eu.telecomnancy.javafx.controller.AccueilAdmin.class)) return accueilAdmin;
        if (ic.equals(eu.telecomnancy.javafx.controller.ChangementMDP.class)) return changementMDP;
        if (ic.equals(eu.telecomnancy.javafx.controller.Demandes.class)) return demandes;
        if (ic.equals(eu.telecomnancy.javafx.controller.DescriptionRDV.class)) return descriptionRDV;
        if (ic.equals(eu.telecomnancy.javafx.controller.Disponibilites.class)) return disponibilites;
        if (ic.equals(eu.telecomnancy.javafx.controller.HistoriqueRDV.class)) return historiqueRDV;
        if (ic.equals(eu.telecomnancy.javafx.controller.PriseRDV.class)) return priseRDV;
        if (ic.equals(eu.telecomnancy.javafx.controller.RecapRDV.class)) return recapRDV;
        if (ic.equals(eu.telecomnancy.javafx.controller.AdminAjoutUtilisateur.class)) return adminAjoutUtilisateur;
        if (ic.equals(eu.telecomnancy.javafx.controller.AdminModifierUtilisateur.class)) return adminModifierUtilisateur;
        if (ic.equals(eu.telecomnancy.javafx.controller.AdminArchive.class)) return adminArchive;
        else return null ;
        });
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 1000);
            // scene.getStylesheets().add(getClass().getResource("/stylesheet/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("exception chargement page " + path );
            System.out.println(e.getMessage());
        }

    }
    
    public void accesAccueilConnexion(){
        String path = "/fxml/Connexion.fxml";
        loadPage(path);
        profRDV.reinitializeInstance();
        profRDV.utilisateur = "";
    }


    public void accesAccueilEtudiant(){       
        String path = "/fxml/EtudiantAccueil.fxml";
        loadPage(path);
        profRDV.setInstance("eleve");
    }

    public void accesAccueilEnseignant(){
        String path = "/fxml/EnseignantAccueil.fxml";
        loadPage(path);
        profRDV.setInstance("prof");
    }

    public void accesAccueilAdmin(){
        String path = "/fxml/AdminAccueil.fxml";
        loadPage(path);
        profRDV.setInstance("admin");
    }

    public void accesChangementMDP(RDV rdv){
        String path = "/fxml/UtilisateurChangementMDP.fxml";
        loadPage(path);
    }

    public void accesDescriptionRDV(RDV rdv){
        this.descriptionRDV = new DescriptionRDV(profRDV,rdv);
        String path = "/fxml/UtilisateurDescriptionRDV.fxml";
        loadPage(path);
    }

    public void accesDemandes(){
        String path = "/fxml/EnseignantDemandes.fxml";
        loadPage(path);
    }

    public void accesDisponibilites(){
        String path = "/fxml/EnseignantDisponibilites.fxml";
        loadPage(path);
    }

    public void accesHistoriqueRDV(){
        String path = "/fxml/UtilisateurHistoriqueRDV.fxml";
        loadPage(path);
    }

    public void accesRecapRDV(){
        String path = "/fxml/EtudiantRecapRDV.fxml";
        loadPage(path);
    }

    public void accesPriseRDV(){
        String path = "/fxml/EtudiantPriseRDV.fxml";
        loadPage(path);
    }
    public void AdminAjoutUtilisateur(){
        String path = "/fxml/AdminAjoutUtilisateur.fxml";
        loadPage(path);
    }

    public void AdminArchive(){
        String path = "/fxml/AdminArchive.fxml";
        loadPage(path);
    }

    public void AdminModifierUtilisateur(){
        String path = "/fxml/AdminModifierUtilisateur.fxml";
        loadPage(path);
    }



}
