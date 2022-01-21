package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.model.RDV;
import eu.telecomnancy.javafx.model.*;
import eu.telecomnancy.javafx.model.utils.DateConversion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class GetterRdv {

    public GetterRdv(){}

    /** Renvoie la liste des rdv pour le user pour la semaine de la date en entrée - Format de la date : yyyy-MM-dd **/
    public static ArrayList<RDV> getRDVWeek(Utilisateur user, String date) throws SQLException {

        int id = getId(user);
        DisponibilityProf getterCreneau = new DisponibilityProf();

        String typeId;
        if (user instanceof Etudiant){
            typeId="id_eleve";
        } else {
            typeId="id_prof";
        }

        String[] dateElement = date.split("-");
        int week = DateConversion.getWeek(date);

        ArrayList<RDV> ListeRDV = new ArrayList<>();
        String sql = "SELECT * FROM rdv inner join availableRDV on rdv.id_prof = availableRDV.id_prof WHERE rdv."+typeId+" = '"+id+"' AND status = 1 "+
                "AND ((4*(CAST(SUBSTR(availableRDV.date,6,2) as decimal)-1)+1+CAST(SUBSTR(availableRDV.date,9,2) as decimal)/7) == "+week+")";

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                RDV rdv = new RDV();
                rdv.archive=(rs.getBoolean("archive"));
                rdv.libelle=(rs.getString("libelle"));
                rdv.lieu=(rs.getString("lieu"));
                // rdv.creneau.id_prof = rs.getInt("id_prof"); NullPointerException
                rdv.enseignant=(GetterUser.getInfoProf(rs.getInt("id_prof")));
                rdv.creneau = getterCreneau.getCreneau(rs.getInt("id_dispo"));
                rdv.etudiant = ((GetterUser.getInfoEleve(rs.getInt("id_eleve"))));
                rdv.description = rs.getString("description");
                ListeRDV.add(rdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(ListeRDV.size());
        return ListeRDV;
    }

    public static RDV getInfoRdv(int idRdv){

        DisponibilityProf getterCreneau = new DisponibilityProf();
        String sql = "SELECT * FROM rdv WHERE id = '"+idRdv+"';";
        RDV rdv = new RDV();
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rdv.archive=(rs.getBoolean("archive"));
                rdv.libelle=(rs.getString("libelle"));
                rdv.lieu=(rs.getString("lieu"));
                rdv.enseignant=(GetterUser.getInfoProf(rs.getInt("id_prof")));
                rdv.creneau = getterCreneau.getCreneau(rs.getInt("id_dispo"));
                rdv.etudiant = ((GetterUser.getInfoEleve(rs.getInt("id_eleve"))));
                rdv.status = (rs.getBoolean("status"));
                rdv.description = rs.getString("description");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rdv;
    }

    public static int getId(Utilisateur user){

        String nom = user.nom;
        String prenom = user.prenom;
        String mail = user.mail;
        Boolean typeUser = user instanceof Etudiant;

        String table;
        if (typeUser){
            table="eleve";
        } else {
            table="prof";
        }

        int id = 0;
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT id FROM "+table+" WHERE nom = '"+nom+"' AND prenom = '"+prenom+"' AND mail = '"+mail+"';";
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

    /** Renvoie la liste des rdv validés non archivés pour le user donné en entrée**/
    public static ArrayList<RDV> getAllRdvValide(Utilisateur user) throws SQLException {

        GetterRdv getterRdv = new GetterRdv();
        GetterUser getterUser = new GetterUser();
        DisponibilityProf getterCreneau = new DisponibilityProf();
        int id = getterRdv.getId(user);

        Boolean typeUser = false;
        if(user instanceof Etudiant){
            typeUser = true;
        }

        String typeId;
        if (typeUser){
            typeId="id_eleve";
        } else {
            typeId="id_prof";
        }

        ArrayList<RDV> ListeRDV = new ArrayList<>();
        String sql = "SELECT * FROM rdv inner join availableRDV on rdv.id_dispo = availableRDV.id WHERE rdv."+typeId+" = '"+id+"'AND status = 1 "+" and archive = 0;";
        //on fait rdv.id_dispo = avlrdv.id; quid de rdv.id_prof = avlrdv.id_prof ?

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                RDV rdv = new RDV();
                rdv.archive = (rs.getBoolean("archive"));
                rdv.libelle = (rs.getString("libelle"));
                rdv.lieu = (rs.getString("lieu"));
                rdv.enseignant = (getterUser.getInfoProf(rs.getInt("id_prof")));
                rdv.etudiant = ((getterUser.getInfoEleve(rs.getInt("id_eleve"))));
                rdv.status = (rs.getBoolean("status"));
                rdv.creneau = getterCreneau.getCreneau(rs.getInt("id_dispo"));
                rdv.description = rs.getString("description");
                ListeRDV.add(rdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListeRDV;
    }

    /** Renvoie la liste de tous les RDV pour le user donné en entrée**/
    public static ArrayList<RDV> getAllRDV(Utilisateur user) throws SQLException {

        GetterRdv getterRdv = new GetterRdv();
        GetterUser getterUser = new GetterUser();
        DisponibilityProf getterCreneau = new DisponibilityProf();
        int id = getterRdv.getId(user);


        Boolean typeUser = false;
        if(user instanceof Etudiant){
            typeUser = true;
        }

        String typeId;
        if (typeUser){
            typeId="id_eleve";
        } else {
            typeId="id_prof";
        }

        ArrayList<RDV> ListeRDV = new ArrayList<>();
        String sql = "SELECT * FROM rdv inner join availableRDV on rdv.id_dispo = availableRDV.id WHERE rdv."+typeId+" = '"+id+"';";
        //on fait rdv.id_dispo = avlrdv.id; quid de rdv.id_prof = avlrdv.id_prof ?

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                RDV rdv = new RDV();
                rdv.archive = (rs.getBoolean("archive"));
                rdv.libelle = (rs.getString("libelle"));
                rdv.lieu = (rs.getString("lieu"));
                rdv.enseignant = (getterUser.getInfoProf(rs.getInt("id_prof")));
                rdv.etudiant = ((getterUser.getInfoEleve(rs.getInt("id_eleve"))));
                rdv.status = (rs.getBoolean("status"));
                rdv.creneau = getterCreneau.getCreneau(rs.getInt("id_dispo"));
                rdv.description = rs.getString("description");
                ListeRDV.add(rdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListeRDV;
    }


    /** Renvoie la liste des rdv non validés non archivés pour le user donné en entrée**/
    public static ArrayList<RDV> getAllRdvNonValide(Utilisateur user) throws SQLException {

        GetterRdv getterRdv = new GetterRdv();
        GetterUser getterUser = new GetterUser();
        DisponibilityProf getterCreneau = new DisponibilityProf();
        int id = getterRdv.getId(user);

        Boolean typeUser = false;
        if(user instanceof Etudiant){
            typeUser = true;
        }

        String typeId;
        if (typeUser){
            typeId="id_eleve";
        } else {
            typeId="id_prof";
        }

        ArrayList<RDV> ListeRDV = new ArrayList<>();
        String sql = "SELECT * FROM rdv inner join availableRDV on rdv.id_dispo = availableRDV.id WHERE rdv."+typeId+" = '"+id+"'AND status = 0 "+" and archive = 0;";
        //on fait rdv.id_dispo = avlrdv.id; quid de rdv.id_prof = avlrdv.id_prof ?

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                RDV rdv = new RDV();
                rdv.archive = (rs.getBoolean("archive"));
                rdv.libelle = (rs.getString("libelle"));
                rdv.lieu = (rs.getString("lieu"));
                rdv.enseignant = (getterUser.getInfoProf(rs.getInt("id_prof")));
                rdv.etudiant = ((getterUser.getInfoEleve(rs.getInt("id_eleve"))));
                rdv.status = (rs.getBoolean("status"));
                rdv.creneau = getterCreneau.getCreneau(rs.getInt("id_dispo"));
                rdv.description = rs.getString("description");
                ListeRDV.add(rdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListeRDV;
    }
}
