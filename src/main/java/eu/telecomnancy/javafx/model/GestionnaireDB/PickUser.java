package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.controller.Erreurs.InsertionException;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PickUser {

    public PickUser(){}

    public static int Pick(String mail) {

        testRegex testeur = new testRegex();
        // try {
        //     testeur.validateMail(mail);
        // } catch (InsertionException e1) {
        //     // TODO Auto-generated catch block
        //     e1.getMessage();
        //     e1.printStackTrace();
        // }

        String typeUser = "";
        int id=0;

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT typeUser FROM connection WHERE mail = '"+mail+"';";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                typeUser = rs.getString("typeUser");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(typeUser);
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT id FROM "+typeUser+" WHERE mail = '"+mail+"';";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                id = rs.getInt("id");
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
