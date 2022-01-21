package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.Creneau;
import eu.telecomnancy.javafx.model.Enseignant;
import eu.telecomnancy.javafx.model.GestionnaireDB.DisponibilityProf;
import eu.telecomnancy.javafx.model.GestionnaireDB.GetterRdv;
import eu.telecomnancy.javafx.model.GestionnaireDB.PickUser;
import eu.telecomnancy.javafx.model.ProfRDV;
import eu.telecomnancy.javafx.model.RDV;
import eu.telecomnancy.javafx.model.utils.DateConversion;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class Disponibilites extends Controlleur implements Initializable {
    private Date date;

    int noOfDays = 7;
    ArrayList<RDV> liste_rdv = new ArrayList<>();
    ArrayList<Creneau> liste_creneau = new ArrayList<>();

    public Disponibilites(ProfRDV profRDV) {

        super(profRDV);
        LocalDate date_now= LocalDate.now(); //get the current date
        ZoneId defaultZoneId = ZoneId.systemDefault();
        date = Date.from(date_now.atStartOfDay(defaultZoneId).toInstant());
    }

    @FXML
    public Button dernier;

    @FXML
    public Button prochain;

    @FXML
    public GridPane grille;

    @FXML
    public Button valider;

    @FXML
    public Label labelYear;

    @FXML
    public DatePicker datePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        ArrayList<Date> list_dates = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date monday=c.getTime();
        list_dates.add(monday);

        c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        Date tuesday=c.getTime();
        list_dates.add(tuesday);

        c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        Date wednesday=c.getTime();
        list_dates.add(wednesday);

        c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        Date thursday=c.getTime();
        list_dates.add(thursday);

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date friday=c.getTime();
        list_dates.add(friday);
        for(Date day : list_dates){
            for(int i=1;i<36;i++){
                Creneau new_creneau = new Creneau(i,monday, PickUser.Pick(profRDV.utilisateur.mail));
                Button button = new Button(new_creneau.getHeure());
                button.setOnAction(event -> {
                    profRDV.disponibilityProf.insertCreneauProf((Enseignant) profRDV.utilisateur,new_creneau,new_creneau);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(new_creneau.date);
                    int dayOfWeek = c1.get(Calendar.DAY_OF_WEEK);
                    removeNodeByRowColumnIndex(new_creneau.indice,dayOfWeek - 2,grille);
                });
                grille.add(button , 0, i);
                grille.setHalignment(button, HPos.CENTER);
                grille.setValignment(button, VPos.CENTER);
            }
        }

        try {
            liste_rdv= GetterRdv.getRDVWeek(this.profRDV.utilisateur, DateConversion.dateToString(date));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not getRDVWeek ");
        }
        try {
            liste_creneau= DisponibilityProf.getProfCreneau(this.profRDV.utilisateur.mail, DateConversion.dateToString(date));
        }
         catch (Exception e) {
            e.printStackTrace();
             System.out.println("could not getProfCreneau");
        }

        int dayOfWeek;





        //remplir RDV
        for(RDV rdv : liste_rdv) {

            c.setTime(rdv.creneau.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            Label l = new Label();
            l.setText("Rendez-Vous " + rdv.creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");

            removeNodeByRowColumnIndex(rdv.creneau.indice,dayOfWeek - 2,grille);
            grille.add(l, dayOfWeek - 2, rdv.creneau.indice);
            grille.setHalignment(l, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(l, VPos.CENTER); // To align vertically in the cell

        }
        //remplir Creneaux déjà dispo
        for(Creneau creneau : liste_creneau) {

            c.setTime(creneau.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            Label l = new Label();
            l.setText("Deja " + creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");

            removeNodeByRowColumnIndex(creneau.indice,dayOfWeek - 2,grille);
            grille.add(l, dayOfWeek - 2, creneau.indice);
            grille.setHalignment(l, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(l, VPos.CENTER); // To align vertically in the cell

        }


    }

    private void update_page() {
        grille.getChildren().clear();
        setDays();
        ArrayList<Date> list_dates = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date monday=c.getTime();
        list_dates.add(monday);

        c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        Date tuesday=c.getTime();
        list_dates.add(tuesday);

        c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        Date wednesday=c.getTime();
        list_dates.add(wednesday);

        c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        Date thursday=c.getTime();
        list_dates.add(thursday);

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date friday=c.getTime();
        list_dates.add(friday);
        for(Date day : list_dates){
            for(int i=1;i<36;i++){
                Creneau new_creneau = new Creneau(i,monday, PickUser.Pick(profRDV.utilisateur.mail));
                Button button = new Button(new_creneau.getHeure());
                button.setOnAction(event -> {
                    profRDV.disponibilityProf.insertCreneauProf((Enseignant) profRDV.utilisateur,new_creneau,new_creneau);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(new_creneau.date);
                    int dayOfWeek = c1.get(Calendar.DAY_OF_WEEK);
                    removeNodeByRowColumnIndex(new_creneau.indice,dayOfWeek - 2,grille);
                });
                grille.add(button , 0, i);
                grille.setHalignment(button, HPos.CENTER);
                grille.setValignment(button, VPos.CENTER);
            }
        }


        try {
            liste_rdv= GetterRdv.getRDVWeek(this.profRDV.utilisateur, DateConversion.dateToString(date));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not getRDVWeek ");
        }
        try {
            liste_creneau= DisponibilityProf.getProfCreneau(this.profRDV.utilisateur.mail, DateConversion.dateToString(date));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("could not getProfCreneau");
        }

        int dayOfWeek;


        //remplir RDV
        for(RDV rdv : liste_rdv) {

            c.setTime(rdv.creneau.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            Label l = new Label();
            l.setText("Rendez-Vous " + rdv.creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");

            removeNodeByRowColumnIndex(rdv.creneau.indice,dayOfWeek - 2,grille);
            grille.add(l, dayOfWeek - 2, rdv.creneau.indice);
            grille.setHalignment(l, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(l, VPos.CENTER); // To align vertically in the cell

        }
        //remplir Creneaux déjà dispo
        for(Creneau creneau : liste_creneau) {

            c.setTime(creneau.date);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)
            Label l = new Label();
            l.setText("Déjà dispo " + creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");

            removeNodeByRowColumnIndex(creneau.indice,dayOfWeek - 2,grille);
            grille.add(l, dayOfWeek - 2, creneau.indice);
            grille.setHalignment(l, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(l, VPos.CENTER); // To align vertically in the cell

        }
    }

    public void removeNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {

        ObservableList<Node> childrens = gridPane.getChildren();
        for(Node node : childrens) {
            //if(node instanceof Button && gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                    //Button b= (Button) node; // use what you want to remove
                    gridPane.getChildren().remove(node);
                    //gridPane.getChildren().remove(b);
                    break;
            }
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
