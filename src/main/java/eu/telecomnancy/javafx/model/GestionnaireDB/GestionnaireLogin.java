package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.controller.Erreurs.ConnexionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
     * Gestionnaire de Login pour accès à la DB.
     */
public class GestionnaireLogin {

    String profType = "prof";
    String eleveType = "eleve";
    String adminType = "admin";

    public GestionnaireLogin() {

    }

    /**
     * Méthode VerifMailMdp qui va aller chercher et vérifier les mots de passes et login dans la base de donnée.
     */
    public String VerifMailMdp(String username, String password) throws SQLException, ConnexionException {

        Connection connection = ConnectionClass.getInstance().getConnection();
        
        Statement statement = connection.createStatement();

        String typeUser = "SELECT typeUser from connection WHERE mail = '" + username + "' and MDP = '" + password + "';";

        try {
            ResultSet resultTypeUser = statement.executeQuery(typeUser);
            String resultType = resultTypeUser.getString("typeUser");
            try { resultTypeUser.close(); } catch (Exception e) { /* Ignored */ }
            return resultType;
        } catch (Exception e) {
            throw new ConnexionException();
        }
    }


    public String login(String username, String password) throws ConnexionException {

        try {
            String type = VerifMailMdp(username, password);
            return type;
        } catch (SQLException e) {
            System.out.println("From VerifMailMdp" +e.getMessage());
        } catch (ConnexionException connexionError){
            throw connexionError;
        }

        return "";
    }
}
