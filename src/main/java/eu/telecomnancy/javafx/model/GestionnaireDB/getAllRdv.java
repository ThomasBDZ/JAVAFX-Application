import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.controller.Erreurs.ConnexionException;
import eu.telecomnancy.javafx.controller.Erreurs.InsertionException;
import eu.telecomnancy.javafx.model.*;
import eu.telecomnancy.javafx.model.GestionnaireDB.DisponibilityProf;
import eu.telecomnancy.javafx.model.utils.DateConversion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class getAllRdv {

    public getAllRdv(){}

    /** Renvoie la liste des rdv validés non archivés pour le user donné en entrée**/
    public ArrayList<RDV> getAllRdvUser(Utilisateur user) throws SQLException {

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
                ListeRDV.add(rdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListeRDV;
    }

    /** Renvoie la liste de tous les RDV pour le user donné en entrée**/
    public ArrayList<RDV> getAllRDV(Utilisateur user) throws SQLException {

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
                ListeRDV.add(rdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListeRDV;
    }


}


