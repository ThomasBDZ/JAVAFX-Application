package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.model.Enseignant;
import eu.telecomnancy.javafx.model.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetterUser {

    public static Enseignant getInfoProf(int idProf){

        String sql = "SELECT * FROM prof WHERE id = '"+idProf+"';";
        Enseignant prof = new Enseignant();
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                prof.setAddresse(rs.getString("adresse"));
                prof.setBirthDate(rs.getString("date"));
                prof.setPrenom(rs.getString("prenom"));
                prof.setNom(rs.getString("nom"));
                prof.setMail(rs.getString("mail"));
                prof.setSexe(rs.getString("sexe"));
                prof.setTelephone(rs.getString("telephone"));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return prof;
    }
    public static Etudiant getInfoEleve(int idEleve){

        String sql = "SELECT * FROM eleve WHERE id = '"+idEleve+"';";
        Etudiant eleve = new Etudiant();
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                eleve.setAddresse(rs.getString("adresse"));
                eleve.setBirthDate(rs.getString("date"));
                eleve.setPrenom(rs.getString("prenom"));
                eleve.setNom(rs.getString("nom"));
                eleve.setMail(rs.getString("mail"));
                eleve.setSexe(rs.getString("sexe"));
                eleve.setTelephone(rs.getString("telephone"));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return eleve;
    }
}
