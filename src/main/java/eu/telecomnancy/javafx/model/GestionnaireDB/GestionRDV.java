package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.controller.Erreurs.ConnexionException;
import eu.telecomnancy.javafx.controller.Erreurs.InsertionException;
import eu.telecomnancy.javafx.model.*;
import eu.telecomnancy.javafx.model.utils.DateConversion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class GestionRDV {

    public GestionRDV(){}

    public void AddRdv(RDV rdv) throws SQLException{

        Boolean status = rdv.status;
        Boolean archive = rdv.archive;
        String lieu = rdv.lieu;
        String libelle = rdv.libelle;
        Enseignant prof = rdv.enseignant;
        Etudiant etudiant = rdv.etudiant;
        Creneau creneau = rdv.creneau;
        int id_creneau = creneau.indice;
        String date = DateConversion.dateToString(creneau.date);
        int id_prof = creneau.id_prof; //= getId(prof);
        int id_eleve = getId(etudiant);

        String sql = "INSERT INTO rdv (id_prof, id_dispo, id_eleve, status, archive, lieu, libelle) values " +
                "('" + id_prof + "','"+id_creneau+"','"+id_eleve+"','"+status+ "','"+archive+ "','"+
                lieu+ "','"+libelle+ "')";

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            int rs = statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getId(Utilisateur user){

        String nom = user.nom;
        String prenom = user.prenom;
        String mail = user.mail;

        Boolean typeUser = false;
        if(user instanceof Etudiant){
            typeUser = true;
        }

        String table;
        if (typeUser){
            table="prof";
        } else {
            table="eleve";
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**Retourne la liste des indices des créneaux disponibles pour un prof en particulier**/
    public ArrayList<Integer> getIdDispo(Creneau creneau){

        ArrayList<Integer> ListeIdDispo = new ArrayList<>();

        int id_prof = creneau.id_prof;

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT id FROM availableRDV WHERE id_prof = '"+id_prof+"';";
            ResultSet rs = statement.executeQuery(sql);
            int count = 0;
            while (rs.next()){
                ListeIdDispo.add(rs.getInt("id"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListeIdDispo;
    }

}
