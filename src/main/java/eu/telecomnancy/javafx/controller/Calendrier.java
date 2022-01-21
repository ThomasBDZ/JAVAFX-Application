package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.Creneau;
import eu.telecomnancy.javafx.model.GestionnaireDB.GetterRdv;
import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.RDV;
import eu.telecomnancy.javafx.model.utils.DateConversion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class Calendrier extends Controlleur{

    public Calendrier(ProfRDV profRDV) {
        super(profRDV);
    }

    @FXML
    private Button prochain;

    @FXML
    private Button dernier;

    @FXML
    private GridPane grille;

    @FXML
    public void initialize(URL url, ResourceBundle rb){


        ArrayList<RDV> liste_rdv = new ArrayList<>();

        LocalDate date_now= LocalDate.now(); //get the current date
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date= new Date();
        date = Date.from(date_now.atStartOfDay(defaultZoneId).toInstant()); //convert from LocalDate type to Date

        //int number_day=date.getDay();

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        try {
            liste_rdv= GetterRdv.getRDVWeek(this.profRDV.utilisateur, DateConversion.dateToString(date));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not getRDVWeek");
        }

        ArrayList<Creneau> liste_creneau_dispnibles= new ArrayList<>();
        liste_creneau_dispnibles=this.profRDV.gestionnaireCreneauDispo.pickDispoWeek(date,this.profRDV.utilisateur);



        

    }
}
