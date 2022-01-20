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
        Enseignant prof = rdv.Enseignant;
        ArrayList<Etudiant> ListeEtudiant;
        ListeEtudiant = rdv.Etudiants;
        Creneau creneau = rdv.creneau;
        Date date = creneau.date;
        int id_prof = creneau.id_prof; //= getId(prof);

        DateConversion newDate = new DateConversion(date);

        java.sql.Date datesql = newDate.javaToSql(date);

        ArrayList<Integer> ListeIdEleve = new ArrayList<>();
        int count = 0;
        for (Etudiant student : ListeEtudiant){
            ListeIdEleve.set(count,getId(student));
        }

        ArrayList<Integer> ListeIdCreneau = getIdDispo(creneau);

        String sql = "INSERT INTO rdv (id_prof, id_dispo, id_eleve, status, archive, lieu, libelle, date, heure) values " +
                "('" + id_prof + "',?,?,'"+status+ "','"+archive+ "','"+
                lieu+ "','"+libelle+ "','"+date+ "','"+creneau.getHeure()+ "')";

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i=0;i<ListeIdCreneau.size();i++){
                for (int j=0;j<ListeIdEleve.size();j++){
                    statement.setInt(1,ListeIdCreneau.get(i));
                    statement.setInt(2,ListeIdEleve.get(j));
                }
                statement.executeBatch();
            }
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
        Boolean typeUser = user.etudiant;

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
