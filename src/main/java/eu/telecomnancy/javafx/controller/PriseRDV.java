package eu.telecomnancy.javafx.controller;

//import org.controlsfx.*;
import org.controlsfx.control.AutoCompletionTextField;
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

/*public class PriseRDV extends Controlleur implements Initializable {
    /*private String enseignant;
    private String etudiant;
    private int heure;
    private int minutes;
    private Date date;
    private ArrayList<String> listeEtudiants;*/
/*    private Date date;
    int noOfDays = 7;
    private ArrayList<RDV> liste_rdv = new ArrayList<>();

    public PriseRDV(ProfRDV profRDV){
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

    /*@FXML
    public TextField enseignant;

    @FXML
    public TextField etudiant;

    @FXML
    public ChoiceBox heure;

    @FXML
    public ChoiceBox minutes;

    @FXML
    public DatePicker datePicker;

    @FXML
    public ListView listeEtudiant;

    @FXML
    public Button valider;

    @FXML
    public Button validerEnseignant;

    @FXML
    public void validerEnseignant(){}*/

/*    @FXML
    public void initialize(URL url, ResourceBundle rb){


        prochain.setOnAction(event -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
            date = calendar.getTime();
            //update_page();
        });

        dernier.setOnAction(event -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, -noOfDays);
            date = calendar.getTime();
           // update_page();
        });
    }

}*/

public class PriseRDV extends Controlleur implements Initializable {
    private Date date;
    int noOfDays = 7;
    ArrayList<RDV> liste_rdv = new ArrayList<>();
    private ArrayList<String> arrayEtudiants;

    public PriseRDV(ProfRDV profRDV) {
        super(profRDV);
        LocalDate date_now= LocalDate.now(); //get the current date
        ZoneId defaultZoneId = ZoneId.systemDefault();
        date = Date.from(date_now.atStartOfDay(defaultZoneId).toInstant());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    }

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
    public TextField enseignant;

    @FXML
    public TextField etudiant;

    @FXML
    public ChoiceBox heure;

    @FXML
    public ChoiceBox minutes;

    @FXML
    public DatePicker datePicker;

    @FXML
    public ListView listeEtudiant;


    @FXML
    private GridPane grille;

    @FXML
    public void initialize(URL url, ResourceBundle rb){


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
            javafx.scene.control.Button b = new javafx.scene.control.Button();
            b.setText("Rendez-Vous " + rdv.creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");
            b.setOnAction(event-> profRDV.getAccesPages().accesDescriptionRDV(rdv));

            grille.add(b, dayOfWeek - 2, rdv.creneau.indice);
            grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(b, VPos.CENTER); // To align vertically in the cell

        }

        new AutoCompletionTextFieldBinding(enseignant, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {
                return Arrays.asList("Option 1", "Option 2");
            }
        });

        validerEtudiant.setOnAction(event -> {
            arrayEtudiants.add(etudiant.getText());
            update_list_view();
        });




    }

    private void update_list_view() {
        ObservableList<String> obvs_etudiants = FXCollections.observableArrayList(arrayEtudiants);
        listeEtudiant.setItems(obvs_etudiants);
    }

    public void update_page() {
        liste_rdv=this.profRDV.gestionnaireRDV.pickRDVWeek(date,this.profRDV.utilisateur);
        Calendar c = Calendar.getInstance();
        int dayOfWeek;
        for(RDV rdv : liste_rdv) {

            c.setTime(rdv.creneau.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            javafx.scene.control.Button b = new Button();
            b.setText("Rendez-Vous " + rdv.creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");
            b.setOnAction(event-> profRDV.getAccesPages().accesDescriptionRDV(rdv));

            grille.add(b, dayOfWeek - 2, rdv.creneau.indice);
            grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(b, VPos.CENTER); // To align vertically in the cell
        }

    }


}


