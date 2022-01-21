package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.Creneau;
import eu.telecomnancy.javafx.model.GestionnaireDB.GetterRdv;
import eu.telecomnancy.javafx.model.ProfRDV;

import eu.telecomnancy.javafx.model.RDV;
import eu.telecomnancy.javafx.model.utils.DateConversion;
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
import java.sql.SQLException;
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
    GridPane grilleOG;

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
    public Button historique;

    @FXML
    private GridPane grille;


    @FXML
    private Label labelYear;

   /*@FXML
    private Label labelLundi;



    @FXML
    private Label labelMardi;

    @FXML
    private Label labelMercredi;

    @FXML
    private Label labelJeudi;

    @FXML
    private Label labelVendredi;*/

    @FXML
    public void initialize(URL url, ResourceBundle rb){
        grilleOG=grille;
        ajouterRDV.setOnAction(event -> {
            profRDV.getAccesPages().accesPriseRDV();
        });

        historique.setOnAction(event -> {
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



        setDays();
        try {
            liste_rdv= GetterRdv.getRDVWeek(this.profRDV.utilisateur, DateConversion.dateToString(date));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not getRDVWeek ");
        }


        Calendar c = Calendar.getInstance();
        int dayOfWeek;
        for(RDV rdv : liste_rdv) {

            c.setTime(rdv.creneau.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            Button b = new Button();
            b.setText("Rendez-Vous " + rdv.creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");
            b.setOnAction(event-> {
                if(rdv!=null){
                    System.out.println("no es null\n");
                    profRDV.getAccesPages().accesDescriptionRDV(rdv);
                }

            });

            grille.add(b, dayOfWeek - 2, rdv.creneau.indice);
            grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(b, VPos.CENTER); // To align vertically in the cell

        }


        }

    public void update_page() {

        grille.getChildren().clear();
        setDays();
        try {
            liste_rdv= GetterRdv.getRDVWeek(this.profRDV.utilisateur, DateConversion.dateToString(date));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not getRDVWeek");
        }
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

    public String convertDateDay(Date date_d){
        //String pattern = "yyyy-MM-dd";
        String pattern = "DD";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(date_d);
        return date;
    }

    public String convertDateMonth(Date date_d){
        //String pattern = "yyyy-MM-dd";
        String pattern = "MM-YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(date_d);

        return date;
    }

    public void setDays(){
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date monday=c.getTime();
        Label Lundi= new Label("Lundi "+convertDateDay(monday));
        grille.add(Lundi, 0, 0);
        grille.setHalignment(Lundi, HPos.CENTER); // To align horizontally in the cell
        grille.setValignment(Lundi, VPos.CENTER); // To align vertically in the cell
        labelYear.setText(convertDateMonth(monday));


        c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        Date tuesday=c.getTime();
        Label Mardi= new Label("Mardi "+convertDateDay(tuesday));
        grille.add(Mardi, 1, 0);
        grille.setHalignment(Mardi, HPos.CENTER); // To align horizontally in the cell
        grille.setValignment(Mardi, VPos.CENTER); // To align vertically in the cell



        c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        Date wednesday=c.getTime();
        Label Mercredi= new Label("Mercredi "+convertDateDay(wednesday));
        grille.add(Mercredi, 2, 0);
        grille.setHalignment(Mercredi, HPos.CENTER); // To align horizontally in the cell
        grille.setValignment(Mercredi, VPos.CENTER); // To align vertically in the cell


        c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        Date thursday=c.getTime();
        Label Jeudi= new Label("Jeudi "+convertDateDay(thursday));
        grille.add(Jeudi, 3, 0);
        grille.setHalignment(Jeudi, HPos.CENTER); // To align horizontally in the cell
        grille.setValignment(Jeudi, VPos.CENTER); // To align vertically in the cell


        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        Date friday=c.getTime();
        Label Vendredi= new Label("Vendredi "+convertDateDay(friday));
        grille.add(Vendredi, 4, 0);
        grille.setHalignment(Vendredi, HPos.CENTER); // To align horizontally in the cell
        grille.setValignment(Vendredi, VPos.CENTER); // To align vertically in the cell

    }


    }





