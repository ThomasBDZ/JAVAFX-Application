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

    public void VerifMailMdp(String username, String password) throws SQLException, ConnexionError {

        Connection connection = ConnectionClass.getInstance().getConnection();

        Statement statement = connection.createStatement();

        String mail = "SELECT * FROM connection WHERE mail = '" + username + "';";
        String mdp = "SELECT * FROM connection WHERE MDP = '" + password + "';";

        ResultSet resultSetMail = statement.executeQuery(mail);
        ResultSet resultSetMdp = statement.executeQuery(mdp);

        if (!(resultSetMail.next())) {
            throw new MailInexistant();
        }
        if (!(resultSetMdp.next())) {
            throw new MauvaisMdp();
        }

        // return le type issus des ResultSet
    }

    public void login(String username, String password, String type) throws SQLException, ConnexionError {


        Connection connection = ConnectionClass.getInstance().getConnection();

        Statement statement = connection.createStatement();

        String sql="SELECT * FROM connection WHERE mail = '"+username+"' AND MDP = '"+ password+"';";
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        try {
            // String type = VerifMailMdp(username, password);
            VerifMailMdp(username, password);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("oups");
        } catch (ConnexionError e){
            throw e;
        }
        
    }
}
