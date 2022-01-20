package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.controller.Erreurs.InsertionException;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PickUser {

    public PickUser(){}

    public void Pick(String nom,String prenom, String mail, String typeUser)throws InsertionException {

        Connection connection = ConnectionClass.getInstance().getConnection();

        testRegex testeur = new testRegex();
        testeur.validateNom(nom);
        testeur.validateNom(prenom);
        testeur.validateMail(mail);

        String sexe = null, date = null, adresse = null, telephone = null;

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM "+typeUser+" WHERE nom = '"+nom+"' AND prenom = '"+prenom+"' AND mail = '"+mail+"';";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                sexe = rs.getString("sexe");
                date = rs.getString("date");
                adresse = rs.getString("adresse");
                telephone = rs.getString("telephone");
            }
            rs.close();
            statement.close();
            //initialiser un client ici avec les données
            System.out.println(new Label(String.format("%s %s %s %s %s %s %s %s ", nom, prenom, mail, sexe, date, adresse,telephone,typeUser)));
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
}
