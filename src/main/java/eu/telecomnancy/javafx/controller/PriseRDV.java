package eu.telecomnancy.javafx.controller;


import eu.telecomnancy.javafx.controller.utils.ProfLabel;
import eu.telecomnancy.javafx.model.*;
import eu.telecomnancy.javafx.model.GestionnaireDB.DisponibilityProf;
import eu.telecomnancy.javafx.model.GestionnaireDB.GetterUser;
import eu.telecomnancy.javafx.model.GestionnaireDB.PickUser;
import eu.telecomnancy.javafx.model.utils.DateConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import org.w3c.dom.Text;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;



public class PriseRDV extends Controlleur implements Initializable {

    private Date date_calendrier;
    int noOfDays = 7;
    ArrayList<Creneau> liste_creneau = new ArrayList<>();
    private Etudiant etudiant;
    private Enseignant enseignant;
    private Creneau creneau;

    public PriseRDV(ProfRDV profRDV) {
        super(profRDV);
        LocalDate date_now= LocalDate.now(); //get the current date
        ZoneId defaultZoneId = ZoneId.systemDefault();
        date_calendrier = Date.from(date_now.atStartOfDay(defaultZoneId).toInstant());
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    }


    @FXML
    public Button prochain;

    @FXML
    public Button dernier;

    @FXML
    public Button valider;

    @FXML
    public Button validerEnseignant;

    @FXML
    public Button validerEtudiant;

    @FXML
    public ChoiceBox<ProfLabel> fieldEnseignant;

    @FXML
    public Label fieldHeure;

    @FXML
    public Label labelSemaine;

    @FXML
    public Label labelDate;

    @FXML
    private GridPane grille;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        /**
         * calendrier
         */
        prochain.setOnAction(event -> {
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
         * enseignants, choiceBox
         */
        ArrayList<Enseignant> arrayEnseignants = new ArrayList<Enseignant>();
        ArrayList<ProfLabel> labels = new ArrayList<ProfLabel>() ;
        arrayEnseignants = GetterUser.getAllProfs();
        for(Enseignant e : arrayEnseignants){
            ProfLabel labelBis = new ProfLabel();
            labelBis.mail = e.mail;
            labelBis.nom = e.nom;
            labelBis.prenom = e.prenom;
            labelBis.enseignant = e;
            labels.add(labelBis);
        }

        ObservableList<ProfLabel> items = FXCollections.observableArrayList(labels);
        fieldEnseignant.setItems(items);

        


        /**
         * si on valide
         */
        validerEnseignant.setOnAction(event -> {
            ProfLabel label = fieldEnseignant.getValue();
            enseignant = label.enseignant;
            String date_string= DateConversion.dateToString(date_calendrier);
            try {
                liste_creneau= DisponibilityProf.getProfCreneau(enseignant.mail,date_string);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("erreur pour get les disponibilités du prof");
            }
            //liste_creneau=this.profRDV.gestionnaireCreneauDispo.pickDispoWeek(date_calendrier,enseignant); //a remplacer par la foction de Thomas getCreneauProf

            Calendar c = Calendar.getInstance();
            int dayOfWeek;
            int weekOfYear;
            weekOfYear=c.get(Calendar.DAY_OF_WEEK);
            labelSemaine.setText("Semaine "+weekOfYear); //on set le label Semaine sur le Calendrier

            for(Creneau creneau_i : liste_creneau) {
                c.setTime(creneau_i.date);
                dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
                Button b = new javafx.scene.control.Button();
                b.setText("Rendez-Vous " + creneau.getHeure());
                b.setOnAction(e-> {
                    creneau=creneau_i;
                    fieldHeure.setText(creneau.getHeure());  //si on sélectionne un RDV (boutton), on change les labels date et heure
                    labelDate.setText(DateConversion.dateToString(creneau.date)); //à vérifier
                });
                grille.add(b, dayOfWeek - 2, creneau_i.indice);
                grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
                grille.setValignment(b, VPos.CENTER); // To align vertically in the cell

            }

        });

        /**
         * etudiant, text field (l'étudiant insère son mail)
         */



            etudiant= (Etudiant) profRDV.utilisateur;




        /**
         * valider
         */
        valider.setOnAction(event -> {
            RDV rdv=new RDV(enseignant,etudiant,creneau);
            profRDV.getAccesPages().accesRecapRDV(rdv);
        });




    }
    private void update_page() {
        ProfLabel label = fieldEnseignant.getValue();
        enseignant = label.enseignant;
        String date_string= DateConversion.dateToString(date_calendrier);
        try {
            liste_creneau= DisponibilityProf.getProfCreneau(enseignant.mail,date_string);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erreur pour get les disponibilités du prof");
        }
        //liste_creneau=this.profRDV.gestionnaireCreneauDispo.pickDispoWeek(date_calendrier,enseignant); //a remplacer par la foction de Thomas getCreneauProf

        Calendar c = Calendar.getInstance();
        int dayOfWeek;
        int weekOfYear;
        weekOfYear=c.get(Calendar.DAY_OF_WEEK);
        labelSemaine.setText("Semaine "+weekOfYear); //on set le label Semaine sur le Calendrier

        for(Creneau creneau_i : liste_creneau) {
            c.setTime(creneau_i.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            Button b = new javafx.scene.control.Button();
            b.setText("Rendez-Vous " + creneau.getHeure());
            b.setOnAction(e-> {
                creneau=creneau_i;
                fieldHeure.setText(creneau.getHeure());  //si on sélectionne un RDV (boutton), on change les labels date et heure
                labelDate.setText(DateConversion.dateToString(creneau.date));
            });
            grille.add(b, dayOfWeek - 2, creneau_i.indice);
            grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(b, VPos.CENTER); // To align vertically in the cell

        }}






}


