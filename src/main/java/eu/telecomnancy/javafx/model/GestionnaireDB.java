package eu.telecomnancy.javafx.model;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.model.ProfRDV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
     * Controleur de la vue Connexion.fxml
     */



public class GestionnaireDB {

    public PasswordField mdp;
    public TextField id;
    public Button Connexion;
    public Label isConnected;
    ProfRDV profRDV;


    @FXML
    MenuItem menuItemAccueil;

    public GestionnaireDB() {
    }


    /**
     * Méthode pour valider la connexion issue de la vue Connexion.fxml.
     * Gère les erreurs qui peuvent être remontée (MailInexistant et MauvaisMdp)
     */



    public void login(PasswordField mdp, TextField id) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM admin WHERE mail = '"+id.getText()+"' AND MDP = '"+ mdp.getText()+"';";
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                isConnected.setText("Connected");
            }else {
                isConnected.setText("Not Connected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
