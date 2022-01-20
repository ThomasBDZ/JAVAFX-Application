package eu.telecomnancy.javafx.controller;

import eu.telecomnancy.javafx.model.ProfRDV;

import eu.telecomnancy.javafx.model.RDV;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class AccueilEnseignant extends Controlleur implements Initializable {

    private Date date;
    int noOfDays = 7;
    ArrayList<RDV> liste_rdv = new ArrayList<>();

    public AccueilEnseignant(ProfRDV profRDV){
        super(profRDV);

        LocalDate date_now= LocalDate.now(); //get the current date
        ZoneId defaultZoneId = ZoneId.systemDefault();
        date = Date.from(date_now.atStartOfDay(defaultZoneId).toInstant());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //Date date = new Date();
        //System.out.println(dateFormat.format(date));


    }

    @FXML
    public void dernier(){
    }

    @FXML
    public void prochain(){


    }

    @FXML
    private GridPane grille;

    @FXML
    private Button prochain;

    @FXML
    private Button dernier;

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
            Button b = new Button();
            b.setText("Rendez-Vous " + rdv.creneau.getHeure());
            //System.out.println("dayOfWeek; "+c.get(Calendar.DAY_OF_WEEK)+"indice: "+rdv.creneau.indice+"\n");
            b.setOnAction(event-> profRDV.getAccesPages().accesDescriptionRDV(rdv));

            grille.add(b, dayOfWeek - 2, rdv.creneau.indice);
            grille.setHalignment(b, HPos.CENTER); // To align horizontally in the cell
            grille.setValignment(b, VPos.CENTER); // To align vertically in the cell

        }

        //ArrayList<RDV> liste_rdv = new ArrayList<>();

        //LocalDate date_now= LocalDate.now(); //get the current date
        //ZoneId defaultZoneId = ZoneId.systemDefault();
        //Date date = Date.from(date_now.atStartOfDay(defaultZoneId).toInstant()); //convert from LocalDate type to Date
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Date date = new Date();
        //System.out.println(dateFormat.format(date)); //2013/10/15 16:16:39
        //int number_day=date.getDay();

        //liste_rdv=this.profRDV.gestionnaireRDV.pickRDVWeek(date,this.profRDV.utilisateur);

        // Calendar calendar = Calendar.getInstance();
        //calendar.setTime(date);
        //int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //day of the week (1=sunday)*/



        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
        //Calendar calendar = new GregorianCalendar(2022,0,21);

       /* int year       = calendar.get(Calendar.YEAR);
        int month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);*/

       /* Creneau creneau=new Creneau(10,date);
        liste_rdv.add(0,new RDV(false,false,false,"salle 1","libelle","enseignant",new ArrayList<>(),creneau));
        Creneau creneau1=new Creneau(3,date);
        liste_rdv.add(0,new RDV(false,false,false,"salle 1","libelle","enseignant",new ArrayList<>(),creneau1));

        */

        //ArrayList<Creneau> liste_creneau_dispnibles= new ArrayList<>();
        //liste_creneau_dispnibles=this.profRDV.gestionnaireCreneauDispo.pickDispoWeek(date,this.profRDV.utilisateur);




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
