package eu.telecomnancy.javafx.controller;


import eu.telecomnancy.javafx.model.Creneau;
import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.RDV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import org.w3c.dom.Text;


import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;



public class PriseRDV extends Controlleur implements Initializable {

    private Date date_calendrier;
    int noOfDays = 7;
    ArrayList<Creneau> liste_creneau = new ArrayList<>();
    private ArrayList<String> arrayEtudiants;
    private ArrayList<String> arrayEnseignants;

    public PriseRDV(ProfRDV profRDV) {
        super(profRDV);
        arrayEtudiants=new ArrayList<>();
        arrayEnseignants=new ArrayList<>();
        LocalDate date_now= LocalDate.now(); //get the current date
        ZoneId defaultZoneId = ZoneId.systemDefault();
        date_calendrier = Date.from(date_now.atStartOfDay(defaultZoneId).toInstant());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
/*
    @FXML
    public javafx.scene.control.Button prochain;

    @FXML
    public javafx.scene.control.Button dernier;

    @FXML
    public Button valider;

    @FXML
    public Button validerEnseignant;

    @FXML
    public Button validerEtudiant;

    @FXML
    public ChoiceBox enseignant;

    @FXML
    public TextField etudiant;

    @FXML
    public Label heure;

    @FXML
    public Label semaine;


   /* @FXML
    public DatePicker datePicker;*/

  /*  @FXML
    public ListView listeEtudiant;


    @FXML
    private GridPane grille;

    @FXML
    public void initialize(URL url, ResourceBundle rb){

        /**
         * calendrier
         */
  /*     prochain.setOnAction(event -> {
            grille.getChildren().clear();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date_calendrier);
            calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
            date_calendrier = calendar.getTime();
            update_page();
        });

        dernier.setOnAction(event -> {
            grille.getChildren().clear();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date_calendrier);
            calendar.add(Calendar.DAY_OF_YEAR, -noOfDays);
            date_calendrier = calendar.getTime();
            update_page();
        });




        /**
         * enseignants
         */
    // arrayEnseignants=;
  /*      ObservableList<String> obvs_enseignants = FXCollections.observableArrayList(arrayEnseignants);
        enseignant=new ChoiceBox<String>();
        enseignant.setItems(obvs_enseignants);

        validerEnseignant.setOnAction(event -> {
            liste_creneau=this.profRDV.gestionnaireCreneauDispo.pickDispoWeek(date_calendrier,this.profRDV.utilisateur);
            Calendar c = Calendar.getInstance();
            int dayOfWeek;
            int weekOfYear;
            weekOfYear=c.get(Calendar.DAY_OF_WEEK);
            semaine.setText("Semaine "+weekOfYear);

            for(Creneau creneau : liste_creneau) {
                c.setTime(creneau.date);
                dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
                javafx.scene.control.Button b = new javafx.scene.control.Button();
                b.setText("Rendez-Vous " + creneau.getHeure());
                b.setOnAction(e-> heure.setText(creneau.getHeure()));
                grille.add(b, dayOfWeek - 2, creneau.indice);
                grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
                grille.setValignment(b, VPos.CENTER); // To align vertically in the cell

            }

        });

        /**
         * etudiants
         */
    /*    validerEtudiant.setOnAction(event -> {
            arrayEtudiants.add(etudiant.getText());
            //update_list_view();
        });


        /**
         * valider
         */
  /*      valider.setOnAction(event -> {

        });




    }

    private void update_page() {
        liste_creneau=this.profRDV.gestionnaireCreneauDispo.pickDispoWeek(date_calendrier,this.profRDV.utilisateur);
        Calendar c = Calendar.getInstance();
        int dayOfWeek;
        for(Creneau creneau : liste_creneau) {

            c.setTime(creneau.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            javafx.scene.control.Button b = new javafx.scene.control.Button();
            b.setText("Rendez-Vous " + creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");
            b.setOnAction(e-> heure.setText(creneau.getHeure()));

            grille.add(b, dayOfWeek - 2, creneau.indice);
            grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(b, VPos.CENTER); // To align vertically in the cell
    }}

  /*  private void update_list_view() {
        ObservableList<String> obvs_etudiants = FXCollections.observableArrayList(arrayEtudiants);
        listeEtudiant.setItems(obvs_etudiants);
    }

*/




}


