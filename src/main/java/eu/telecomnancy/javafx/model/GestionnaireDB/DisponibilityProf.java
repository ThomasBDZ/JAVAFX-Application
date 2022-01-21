package eu.telecomnancy.javafx.model.GestionnaireDB;

import java.sql.*;
import java.util.ArrayList;
import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.controller.Erreurs.InsertionException;
import eu.telecomnancy.javafx.model.*;
import eu.telecomnancy.javafx.model.utils.DateConversion;

public class DisponibilityProf {

    public DisponibilityProf(){}

    /**add in availableRDV table prof's free appointments**/
    public void insertCreneauProf(Enseignant prof, int heureDebut, int heureFin, String date){

        String profName = prof.nom;
        String profPrenom = prof.prenom;
        String profMail = prof.mail;
        int id_prof = getIdProf(prof);

        String preQuerySql = "SELECT * from availableRDV where indice >= "+heureDebut+" and indice <= "+heureFin+" " +
                "and (CAST(SUBSTR(date,6,2) as decimal)) == "+DateConversion.getMonth(date)+" and (CAST(SUBSTR(date,9,2) as decimal)) == "+DateConversion.getDay(date)+";";
        String sql = "INSERT INTO availableRDV ( id_prof, indice, date) values (?,?,?);";

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement2 = connection.createStatement();
            ResultSet rs = statement2.executeQuery(preQuerySql);
            if(!rs.next()){ // Si les créneaux ne sont pas déjà présents
                rs.close();
                PreparedStatement statement = connection.prepareStatement(sql);
                for (int indice = heureDebut;indice<heureFin;indice++){
                    statement.setInt(1,id_prof);
                    statement.setInt(2, indice);
                    statement.setString(3, date);
                    statement.addBatch();
                    if (indice == heureFin-1){
                        statement.executeBatch();
                    }
                }
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Creneau> getProfCreneau(String mailProf, String dateCreneau) throws Exception {

        int id_prof = PickUser.Pick(mailProf);
        String date;
        int indice;
        int week = DateConversion.getWeek(dateCreneau);

        String sql = "SELECT * FROM availableRDV WHERE id_prof = " + id_prof + " AND "+
                "(4*(CAST(SUBSTR(date,6,2) as decimal)-1)+1+CAST(SUBSTR(date,9,2) as decimal)/7) == "+week+";";


        ArrayList<Creneau> ListeCreneau = new ArrayList<>();

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                id_prof = rs.getInt("id_prof");
                indice = rs.getInt("indice");
                date = rs.getString("date");
                java.util.Date dateBis = DateConversion.stringToDate(date);
                Creneau creneau = new Creneau(indice,dateBis,id_prof);
                ListeCreneau.add(creneau);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListeCreneau;
    }


    public int getIdProf(Enseignant prof){

        String nom = prof.nom;
        String prenom = prof.prenom;
        String mail = prof.mail;

        int id = 0;
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT id FROM prof WHERE nom = '"+nom+"' AND prenom = '"+prenom+"' AND mail = '"+mail+"';";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                id = rs.getInt("id");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Creneau getCreneau(int id_creneau){

        String sql = "SELECT * FROM availableRDV WHERE id = " + id_creneau +";";
        int id_prof = 0,indice = 0;
        String date = "";

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                id_prof = rs.getInt("id_prof");
                indice = rs.getInt("indice");
                date = rs.getString("date");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Creneau creneau = new Creneau(indice,DateConversion.stringToDate(date),id_prof );
        return creneau;
    }

}
