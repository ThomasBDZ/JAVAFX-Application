package eu.telecomnancy.javafx.controller;


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
    public ChoiceBox FieldEnseignant;

    @FXML
    public TextField FieldEtudiant;

    @FXML
    public Label FieldDate;

    @FXML
    public Label FieldHeure;

    @FXML
    public Label LabelSemaine;

    @FXML
    public Label LabelDate;

    @FXML
    private GridPane grille;

    @FXML
    public void initialize(URL url, ResourceBundle rb){

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
        ArrayList<Enseignant> arrayEnseignants = new ArrayList<>();
        arrayEnseignants = GetterUser.getAllProfs();
        ArrayList<String> arrayEnseignants_nom = new ArrayList<>();
        for(Enseignant e : arrayEnseignants){
            arrayEnseignants_nom.add( e.prenom+" "+e.nom+","+e.mail);
        }
        ObservableList<String> obvs_enseignants = FXCollections.observableArrayList(arrayEnseignants_nom);
        //FieldEnseignant=new ChoiceBox<String>();
        FieldEnseignant.setItems(obvs_enseignants);

        /**
         * si on valide
         */
        validerEnseignant.setOnAction(event -> {
            String enseignant_nom_mail= (String) FieldEnseignant.getValue(); //pas sur si ça marche, à tester, on obtient prenom+nom , mail
            String[] nom_mail = enseignant_nom_mail.split(",");
            int id_prof = PickUser.Pick(nom_mail[1]);
            enseignant= GetterUser.getInfoProf(id_prof);
            String date_string= DateConversion.dateToString(date_calendrier);
            try {
                liste_creneau= DisponibilityProf.getProfCreneau(nom_mail[1],date_string);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("erreur pour get les disponibilités du prof");
            }
            liste_creneau=this.profRDV.gestionnaireCreneauDispo.pickDispoWeek(date_calendrier,enseignant); //a remplacer par la foction de Thomas getCreneauProf

            Calendar c = Calendar.getInstance();
            int dayOfWeek;
            int weekOfYear;
            weekOfYear=c.get(Calendar.DAY_OF_WEEK);
            LabelSemaine.setText("Semaine "+weekOfYear); //on set le label Semaine sur le Calendrier

            for(Creneau creneau_i : liste_creneau) {
                c.setTime(creneau_i.date);
                dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
                Button b = new javafx.scene.control.Button();
                b.setText("Rendez-Vous " + creneau.getHeure());
                b.setOnAction(e-> {
                    creneau=creneau_i;
                    FieldHeure.setText(creneau.getHeure());  //si on sélectionne un RDV (boutton), on change les labels date et heure
                    FieldDate.setText(creneau.date.toString()); //à vérifier
                });
                grille.add(b, dayOfWeek - 2, creneau_i.indice);
                grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
                grille.setValignment(b, VPos.CENTER); // To align vertically in the cell

            }

        });

        /**
         * etudiant, text field (l'étudiant insère son mail)
         */
        validerEtudiant.setOnAction(event -> {
            String etudiant_mail=FieldEtudiant.getText();
            //etudiant=getUser(etudiant_mail);

        });


        /**
         * valider
         */
       valider.setOnAction(event -> {
           RDV rdv=new RDV(enseignant,etudiant,creneau);
            profRDV.getAccesPages().accesRecapRDV(rdv);
        });




    }

    private void update_page() {
        String enseignant_nom_mail= (String) FieldEnseignant.getValue(); //pas sur si ça marche, à tester, on obtient prenom+nom , mail
        String[] nom_mail = enseignant_nom_mail.split(",");
        int id_prof = PickUser.Pick(nom_mail[1]);
        enseignant= GetterUser.getInfoProf(id_prof);
        String date_string= DateConversion.dateToString(date_calendrier);
        try {
            liste_creneau= DisponibilityProf.getProfCreneau(nom_mail[1],date_string);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erreur pour get les disponibilités du prof");
        }
        liste_creneau=this.profRDV.gestionnaireCreneauDispo.pickDispoWeek(date_calendrier,enseignant); //a remplacer par la foction de Thomas getCreneauProf

        Calendar c = Calendar.getInstance();
        int dayOfWeek;
        int weekOfYear;
        weekOfYear=c.get(Calendar.DAY_OF_WEEK);
        LabelSemaine.setText("Semaine "+weekOfYear); //on set le label Semaine sur le Calendrier

        for(Creneau creneau_i : liste_creneau) {
            c.setTime(creneau_i.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            Button b = new javafx.scene.control.Button();
            b.setText("Rendez-Vous " + creneau.getHeure());
            b.setOnAction(e-> {
                creneau=creneau_i;
                FieldHeure.setText(creneau.getHeure());  //si on sélectionne un RDV (boutton), on change les labels date et heure
                FieldDate.setText(creneau.date.toString()); //à vérifier
            });
            grille.add(b, dayOfWeek - 2, creneau_i.indice);
            grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(b, VPos.CENTER); // To align vertically in the cell

        }}








}


