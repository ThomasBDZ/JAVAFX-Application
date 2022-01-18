package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.ConnectionClass;

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

    public void VerifMailMdp(String username, String password) throws SQLException {

        Connection connection = ConnectionClass.getInstance().getConnection();

        Statement statement = connection.createStatement();

        String mail = "SELECT * FROM connection WHERE mail = '" + username + "';";
        String mdp = "SELECT * FROM connection WHERE MDP = '" + password + "';";

        ResultSet resultSetMail = statement.executeQuery(mail);
        ResultSet resultSetMdp = statement.executeQuery(mdp);

        if (!(resultSetMail.next())) {
            System.out.println("MailInexistant");
        }
        if (!(resultSetMdp.next())) {
            System.out.println("MauvaisMdp");
        }
    }

    public void login(String username, String password, String type) throws SQLException {



        Connection connection = ConnectionClass.getInstance().getConnection();

        Statement statement = connection.createStatement();

        String sql="SELECT * FROM connection WHERE mail = '"+username+"' AND MDP = '"+ password+"';";
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);

        if (type.equals(adminType)) {
            if (resultSet.next()) {
                System.out.println("Aller dans le menu admin");
            } else {
                VerifMailMdp(username, password);
            }
        }else if (type.equals(eleveType)){
            if (resultSet.next()){
                System.out.println("Aller dans le menu eleve");
            }else {
                VerifMailMdp(username, password);
            }
        }else if (type.equals(profType)){
            if (resultSet.next()){
                System.out.println("Aller dans le menu prof");
            }else {
                VerifMailMdp(username, password);
            }
        }else{
            System.out.println("Connexion Error");
        }
    }
}
