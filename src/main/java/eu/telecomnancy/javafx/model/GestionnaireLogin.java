package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.controller.Erreurs.ConnexionError;
import eu.telecomnancy.javafx.controller.Erreurs.MailInexistant;
import eu.telecomnancy.javafx.controller.Erreurs.MauvaisMdp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
     * Gestionnaire de la vue Connexion.fxml
     */



public class GestionnaireLogin {

    String profType = "prof";
    String eleveType = "eleve";
    String adminType = "admin";

    public GestionnaireLogin() {
    }

    public String VerifMailMdp(String username, String password) throws SQLException, ConnexionError {

        Connection connection = ConnectionClass.getInstance().getConnection();

        Statement statement = connection.createStatement();

        String typeUser = "select typeUser from connection where mail = '" + username + "' and MDP = '" + password + "';";

        String mail = "SELECT mail FROM connection WHERE mail = '" + username + "';";
        String mdp = "SELECT MDP FROM connection WHERE MDP = '" + password + "';";
        
        ResultSet resultSetMail = statement.executeQuery(mail);
        ResultSet resultSetMdp = statement.executeQuery(mdp);
        ResultSet resultTypeUser = statement.executeQuery(typeUser);
        
        if (!(resultSetMail.next())) {
            throw new MailInexistant();
        }
        if (!(resultSetMdp.next())) {
            throw new MauvaisMdp();
        }
        String result;
        result = resultTypeUser.getString(0);
        return result;
    }

    public String login(String username, String password) throws SQLException, ConnexionError {


        Connection connection = ConnectionClass.getInstance().getConnection();

        Statement statement = connection.createStatement();

        String sql="SELECT * FROM connection WHERE mail = '"+username+"' AND MDP = '"+ password+"';";
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        String type = "------";
        try {
            type = VerifMailMdp(username, password);
        } catch (SQLException e) {
            //TODO: handle exception
        } catch (ConnexionError e){
            throw e;
        }

        return type;
    }
}
