package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.model.Enseignant;
import eu.telecomnancy.javafx.model.Etudiant;
import eu.telecomnancy.javafx.model.RDV;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeStatus {

    public ChangeStatus(){}


    /** Si le rdv est terminé, on change son status**/
    public static void rdvFinish(RDV rdv) {

        int idRdv = getIdRdv(rdv);

        String sql = "UPDATE rdv SET status = false WHERE id = '"+idRdv+"';";

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** pour activer un rdv**/
    public static void rdvReady(RDV rdv) {

        int idRdv = getIdRdv(rdv);

        String sql = "UPDATE rdv SET status = true WHERE id = '"+idRdv+"';";

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** Si le rdv est archivé, on change son archive**/
    public static void archiveYes(RDV rdv) {

        int idRdv = getIdRdv(rdv);

        String sql = "UPDATE rdv SET archive = true WHERE id = '"+idRdv+"';";

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** Si le rdv n'est pas archivé, on change son archive**/
    public static void archiveNo(RDV rdv) {

        int idRdv = getIdRdv(rdv);
        String sql = "UPDATE rdv SET archive = false WHERE id = '"+idRdv+"';";

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**Obtenir l'id du rdv**/
    public static int getIdRdv(RDV rdv){


        GestionRDV grdv = new GestionRDV();
        Enseignant prof = new Enseignant();
        Etudiant eleve = new Etudiant();
        prof = rdv.enseignant;
        eleve = rdv.etudiant;
        String libelle = rdv.libelle;
        int id_prof = grdv.getId(prof);
        int id_eleve = grdv.getId(eleve);

        int id = 0;
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT id FROM rdv WHERE id_prof = '"+id_prof+"' AND id_eleve = '"+id_eleve+"' AND libelle = '"+libelle+"';";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                id = rs.getInt("id");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
