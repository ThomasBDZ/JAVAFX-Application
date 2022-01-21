import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.controller.Erreurs.ConnexionException;
import eu.telecomnancy.javafx.controller.Erreurs.InsertionException;
import eu.telecomnancy.javafx.model.*;
import eu.telecomnancy.javafx.model.utils.DateConversion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class getAllRdv {

    public getAllRdv(){}

    /** Renvoie la liste des rdv validés non archivés pour le user donné en entrée**/
    public ArrayList<RDV> getAllRdvUser(Utilisateur user) throws SQLException {

        GestionRDV grdv = new GestionRDV();
        int id = grdv.getId(user);

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
        String sql = "SELECT * FROM rdv WHERE "+typeId+" = '"+id+"' AND status = 1 "+" and archive = 0;";
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                RDV rdv = new RDV();
                rdv.setArchive(rs.getBoolean("archive"));
                rdv.setLibelle(rs.getString("libelle"));
                rdv.setLieu(rs.getString("lieu"));
                // rdv.creneau.id_prof = rs.getInt("id_prof"); NullPointerException
                rdv.setEnseignant(grdv.getInfoProf(rs.getInt("id_prof")));
                rdv.setEtudiants((grdv.getInfoEleve(rs.getInt("id_eleve"))));// NullPointerException
                ListeRDV.add(rdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(ListeRDV.size());
        return ListeRDV;
    }

    public static void main(String[] args) throws SQLException {

        getAllRdv getThemAll = new getAllRdv();
        GestionRDV grdv = new GestionRDV();
        Enseignant oster = new Enseignant();
        oster = grdv.getInfoProf(1);
        ArrayList<RDV> listeRdvOster = new ArrayList<>();
        listeRdvOster = getThemAll.getAllRdv(oster);


    }


}


