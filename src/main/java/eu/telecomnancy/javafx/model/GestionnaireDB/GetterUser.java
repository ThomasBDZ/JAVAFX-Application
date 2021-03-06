package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.model.Enseignant;
import eu.telecomnancy.javafx.model.Etudiant;
import eu.telecomnancy.javafx.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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


    public static String getMdp(Utilisateur user){

        String mail = user.mail;
        String userType;
        if(user instanceof Etudiant){
            userType= "eleve";
        } else{
            userType = "prof";
        }
        String mdp ="empty MDP";
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT MDP FROM connection WHERE mail = '"+mail+"' AND typeUser = '"+userType+ "';";
            ResultSet rs = statement.executeQuery(sql);
            mdp = rs.getString("mdp");
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mdp;
    }
    public static ArrayList<Enseignant> getAllProfs(){

        String sql = "SELECT * FROM prof";
        ArrayList<Enseignant> listeProf = new ArrayList<>();

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Enseignant prof = new Enseignant();
                prof.setAddresse(rs.getString("adresse"));
                prof.setBirthDate(rs.getString("date"));
                prof.setPrenom(rs.getString("prenom"));
                prof.setNom(rs.getString("nom"));
                prof.setMail(rs.getString("mail"));
                prof.setSexe(rs.getString("sexe"));
                prof.setTelephone(rs.getString("telephone"));
                listeProf.add(prof);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listeProf;
    }

    public static ArrayList<Etudiant> getAllEleves(){

        String sql = "SELECT * FROM eleve";
        ArrayList<Etudiant> listeEleve = new ArrayList<>();

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Etudiant eleve = new Etudiant();
                eleve.setAddresse(rs.getString("adresse"));
                eleve.setBirthDate(rs.getString("date"));
                eleve.setPrenom(rs.getString("prenom"));
                eleve.setNom(rs.getString("nom"));
                eleve.setMail(rs.getString("mail"));
                eleve.setSexe(rs.getString("sexe"));
                eleve.setTelephone(rs.getString("telephone"));
                listeEleve.add(eleve);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listeEleve;
    }

}
