package eu.telecomnancy.javafx.model.GestionnaireDB;

import java.sql.*;
import java.util.ArrayList;
import eu.telecomnancy.javafx.ConnectionClass;

public class DisponibilityProf {

    public final static int HEURE_MIN = 8; // 8H
    public final static int HEURE_MAX = 20; // 20H
    public final static int INDICE_MAX = HEURE_MAX*3 ;

    public DisponibilityProf(){}
    
    /**add in availableRDV table prof's free appointments**/
    //TODO: mettre date au lieu de String
    public void insertCreneauProf(String profName,String profPrenom,String profMail, int heureDebut, int heureFin, String date){
        //TODO : adapter avec en entré objet utilisateur + créneau
        String sql = "INSERT INTO availableRDV ( id_prof, indice, date) values (?,?,?);";

        int id_prof = getIdProf(profName,profPrenom,profMail);

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int indice = heureDebut+1;indice<heureFin+1;indice++){
                statement.setInt(1,id_prof);
                statement.setInt(2, indice);
                statement.setString(3, date);
                statement.addBatch();
                System.out.println(indice);
                if (indice == heureFin){
                    statement.executeBatch();
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Creneaux> getProfCreneau(String profName, String profPrenom, String profMail){

        int id_prof = getIdProf(profName,profPrenom,profMail);
        String sql = "SELECT * FROM availableRDV WHERE id_prof = '" + id_prof + "';";
        String date = null;
        int indice,id = 0;
        ArrayList<Creneaux> ListeCreneau = new ArrayList<>();

        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                id = rs.getInt("id");
                id_prof = rs.getInt("id_prof");
                indice = rs.getInt("indice");
                date = rs.getString("date");
                Creneaux creneau = new Creneaux(id,id_prof,indice,date);
                ListeCreneau.add(creneau);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListeCreneau;
    }

    public int getIdProf(String nom,String prenom, String mail){

        int id = 0;
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT id FROM prof WHERE nom = '"+nom+"' AND prenom = '"+prenom+"' AND mail = '"+mail+"';";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                id = rs.getInt("id");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
