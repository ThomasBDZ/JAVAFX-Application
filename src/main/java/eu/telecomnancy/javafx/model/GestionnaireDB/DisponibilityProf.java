package eu.telecomnancy.javafx.model.GestionnaireDB;

import java.sql.*;
import java.util.ArrayList;
import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.model.utils.DateConversion;
import eu.telecomnancy.javafx.model.*;

public class DisponibilityProf {

    public DisponibilityProf(){}
    
    /**add in availableRDV table prof's free appointments**/
    public void insertCreneauProf(Enseignant prof, Creneau creneauDebut, Creneau creneauFin){

        String profName = prof.nom;
        String profPrenom = prof.prenom;
        String profMail = prof.mail;
        int heureDebut = creneauDebut.indice;
        int heureFin = creneauFin.indice;
        java.util.Date date = creneauDebut.date;

        String sql = "INSERT INTO availableRDV ( id_prof, indice, date) values (?,?,?);";

        int id_prof = getIdProf(prof);
        DateConversion newDate = new DateConversion(date);

        java.sql.Date datesql = newDate.javaToSql(date);

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int indice = heureDebut+1;indice<heureFin+1;indice++){
                statement.setInt(1,id_prof);
                statement.setInt(2, indice);
                statement.setDate(3, datesql);
                statement.addBatch();
                if (indice == heureFin){
                    statement.executeBatch();
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Creneau> getProfCreneau(Enseignant prof){

        String profName = prof.nom;
        String profPrenom = prof.prenom;
        String profMail = prof.mail;

        int id_prof = getIdProf(prof);
        String sql = "SELECT * FROM availableRDV WHERE id_prof = '" + id_prof + "';";
        Date date = null;
        int indice= 0;

        ArrayList<Creneau> ListeCreneau = new ArrayList<>();

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                id_prof = rs.getInt("id_prof");
                indice = rs.getInt("indice");
                date = rs.getDate("date");
                DateConversion newDate = new DateConversion(date);
                java.util.Date dateJava = newDate.sqlToJava(date);
                Creneau creneau = new Creneau(indice,dateJava,id_prof);
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
}
