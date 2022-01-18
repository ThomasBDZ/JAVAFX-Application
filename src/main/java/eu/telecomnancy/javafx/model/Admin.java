package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.controller.Erreurs.ConnexionError;
import eu.telecomnancy.javafx.controller.Erreurs.MailInexistant;
import eu.telecomnancy.javafx.controller.Erreurs.MauvaisMdp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Admin {

    public Admin(){

    }

    public void Add(String nom, String prenom, String mail, int sexe, String date,String address, String tel, String table) throws SQLException {

        Connection connection = ConnectionClass.getInstance().getConnection();


        try {
            Statement statement2 = connection.createStatement();
            String preQueryStatement = "SELECT * FROM " + table + " WHERE nom = '" + nom + "' AND prenom = '" + prenom + "';";
            ResultSet rs = statement2.executeQuery(preQueryStatement);
            if (!rs.next()) {
                Statement statement = connection.createStatement();
                String sql = "INSERT INTO " + table + " (nom, prenom, sexe, date, adresse, mail, telephone) values " +
                        "('" + nom + "','" + prenom + "','" + sexe + "','" + date + "','" + address + "','" + mail + "','" + tel + "')";

                int status = statement.executeUpdate(sql);
                if (status > 0) {
                    System.out.println(table + " registered");
                }
            }else {
                System.out.println(table + "'s user already in data base ( "+nom+" "+prenom+" )");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    /** Ici Oldelement = mail, telephone ou adresse, newElement aussi (le nouveau), table = eleve ou professeur**/
    public void update(String nom, String prenom, String newElementValeur, String table, String oldElement){

        Connection connection = ConnectionClass.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE '"+table+"' SET '"+oldElement+"' = '"+newElementValeur+"' WHERE nom = '"+nom+"' AND prenom = '"+prenom+"';";
            int status = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void delete(String nom, String prenom, String table){

        Connection connection = ConnectionClass.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM '"+table+"' WHERE nom = '"+nom+"' AND prenom = '"+prenom+"';";
            int status = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
