package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.Creneau;
import eu.telecomnancy.javafx.model.ProfRDV;

import eu.telecomnancy.javafx.model.RDV;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;

public class AccueilEtudiant extends Controlleur implements Initializable {
    private Date date;
    int noOfDays = 7;
    ArrayList<RDV> liste_rdv = new ArrayList<>();

    public AccueilEtudiant(ProfRDV profRDV) {
        super(profRDV);
        LocalDate date_now= LocalDate.now(); //get the current date
        ZoneId defaultZoneId = ZoneId.systemDefault();
        date = Date.from(date_now.atStartOfDay(defaultZoneId).toInstant());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    }


    @FXML
    public Button prochain;

    @FXML
    public Button dernier;

    @FXML
    public Button ajouterRDV;

    @FXML
    public Button Historique;

    @FXML
    private GridPane grille;

    @FXML
    public void initialize(URL url, ResourceBundle rb){

        ajouterRDV.setOnAction(event -> {
            profRDV.getAccesPages().accesPriseRDV();
        });

        Historique.setOnAction(event -> {
            profRDV.getAccesPages().accesHistoriqueRDV();
        });


        prochain.setOnAction(event -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
            date = calendar.getTime();
            update_page();
        });

        dernier.setOnAction(event -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, -noOfDays);
            date = calendar.getTime();
            update_page();
        });


        liste_rdv=this.profRDV.gestionnaireRDV.pickRDVWeek(date,this.profRDV.utilisateur);
        Calendar c = Calendar.getInstance();
        int dayOfWeek;
        for(RDV rdv : liste_rdv) {

            c.setTime(rdv.creneau.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            Button b = new Button();
            b.setText("Rendez-Vous " + rdv.creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");
            b.setOnAction(event-> profRDV.getAccesPages().accesDescriptionRDV(rdv));

            grille.add(b, dayOfWeek - 2, rdv.creneau.indice);
            grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(b, VPos.CENTER); // To align vertically in the cell

        }


        }

    public void update_page() {
        liste_rdv=this.profRDV.gestionnaireRDV.pickRDVWeek(date,this.profRDV.utilisateur);
        Calendar c = Calendar.getInstance();
        int dayOfWeek;
        for(RDV rdv : liste_rdv) {

            c.setTime(rdv.creneau.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            Button b = new Button();
            b.setText("Rendez-Vous " + rdv.creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");
            b.setOnAction(event-> profRDV.getAccesPages().accesDescriptionRDV(rdv));

            grille.add(b, dayOfWeek - 2, rdv.creneau.indice);
            grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(b, VPos.CENTER); // To align vertically in the cell
        }

    }


    }


