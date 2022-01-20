package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.controller.Erreurs.ConnexionException;
import eu.telecomnancy.javafx.controller.Erreurs.InsertionException;
import eu.telecomnancy.javafx.model.Etudiant;
import eu.telecomnancy.javafx.model.Utilisateur;
import eu.telecomnancy.javafx.model.utils.DateConversion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class ModificationUsers {

    public ModificationUsers(){

    }

    public void Add(Utilisateur user) throws SQLException, InsertionException{

        String nom = user.nom;
        String prenom = user.prenom;
        String mail = user.mail;
        String sexe = user.sexe;
        String address = user.addresse;
        String tel = user.telephone;
        Boolean typeUser = false;
        if(user instanceof Etudiant){
            typeUser = true;
        }
        String date = user.birthDate;


        Connection connection = ConnectionClass.getInstance().getConnection();
        testRegex testeur = new testRegex();
        testeur.validateTel(tel);
        testeur.validateAddress(address);
        testeur.validateMail(mail);
        testeur.validateSexe(sexe);
        testeur.validateNom(nom);
        testeur.validateNom(prenom);

        String table;
        if (!typeUser){
            table="prof";
        } else {
            table="eleve";
        }

        try {
            Statement statement2 = connection.createStatement();
            String preQueryStatement = "SELECT * FROM " + table + " WHERE nom = '" + nom + "' AND prenom = '" + prenom + "';";
            ResultSet rs = statement2.executeQuery(preQueryStatement);
            if (!rs.next()) { // si il n'y pas deja l'info dans la bdd
                Statement statement = connection.createStatement();
                String sql = "INSERT INTO " + table + " (nom, prenom, sexe, date, adresse, mail, telephone) values " +
                        "('" + nom + "','" + prenom + "','" + sexe + "','" + date + "','" + address + "','" + mail + "','" + tel + "'); INSERT INTO" +
                        " connection"+ " (mail, MDP, typeUser) values ('" + mail + "','" + nom + "','" + table + "');";

                int status = statement.executeUpdate(sql);
                if (status == 0) {
                    throw new InsertionException(table,nom,prenom);
                }
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
    public void update(Utilisateur user, String newElementValeur,  String oldElement) throws InsertionException{

        String nom = user.nom;
        String prenom = user.prenom;
        Boolean typeUser = false;
        if(user instanceof Etudiant){
            typeUser = true;
        }


        Connection connection = ConnectionClass.getInstance().getConnection();
        testRegex testeur = new testRegex();
        testeur.validateNom(nom);
        testeur.validateNom(prenom);

        String table;
        if (!typeUser){
            table="prof";
        } else {
            table="eleve";
        }

        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE '"+table+"' SET '"+oldElement+"' = '"+newElementValeur+"' WHERE nom = '"+nom+"' AND prenom = '"+prenom+"';";
            statement.executeUpdate(sql);
            if (oldElement.equals("mail")){
                Statement updateMail = connection.createStatement();
                String sqlUpdate = "UPDATE connection SET mail = '"+newElementValeur+"' WHERE MDP = '"+nom+"' AND typeUser = '"+typeUser+"';";
                updateMail.executeUpdate(sqlUpdate);
            }
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

    public void delete(Utilisateur user) throws InsertionException{

        String nom = user.nom;
        String prenom = user.prenom;

        Boolean typeUser = false;
        if(user instanceof Etudiant){
            typeUser = true;
        }

        String table;
        if (!typeUser){
            table="prof";
        } else {
            table="eleve";
        }

        Connection connection = ConnectionClass.getInstance().getConnection();
        testRegex testeur = new testRegex();
        testeur.validateNom(nom);
        testeur.validateNom(prenom);
        String sexe = null, date = null, adresse = null, mail = null, telephone = null;

        try {
            Statement statementArchive = connection.createStatement();
            String sqlArchive = "SELECT * FROM "+table+" WHERE nom = '"+nom+"' AND prenom = '"+prenom+"';";
            ResultSet rs = statementArchive.executeQuery(sqlArchive);
            while (rs.next()){
                sexe = rs.getString("sexe");
                date = rs.getString("date");
                adresse = rs.getString("adresse");
                mail = rs.getString("mail");
                telephone = rs.getString("telephone");
            }
            rs.close();
            String sqlArhiveUpdate = "INSERT INTO archive (nom, prenom, sexe, date, adresse, mail, telephone, typeUser) values " +
                    "('" + nom + "','" + prenom + "','" + sexe + "','" + date + "','" + adresse + "','" + mail + "','" + telephone + "','" + table +"');";
            statementArchive.executeUpdate(sqlArhiveUpdate);
            statementArchive.close();
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM '"+table+"' WHERE nom = '"+nom+"' AND prenom = '"+prenom+"'; DELETE FROM connection WHERE" +
                    " MDP = '"+nom+"';";
            statement.executeUpdate(sql);
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

    public void modifMdp(Utilisateur user, String newMdp) throws InsertionException {

        String mail = user.mail;
        testRegex testeur = new testRegex();
        testeur.validateMail(mail);

        String sql = "UPDATE connection SET MDP = '"+newMdp+"' WHERE mail = '"+mail+"';";

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
