package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.model.RDV;
import eu.telecomnancy.javafx.model.*;
import eu.telecomnancy.javafx.model.utils.DateConversion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class GetterRdv {

    public GetterRdv(){}

    /** Renvoie la liste des rdv pour le user pour la semaine de la date en entrée - Format de la date : yyyy-MM-dd **/
    public static ArrayList<RDV> getRDVWeek(Utilisateur user, String date) throws SQLException {

        int id = getId(user);

        String typeId;
        if (user instanceof Etudiant){
            typeId="id_eleve";
        } else {
            typeId="id_prof";
        }
        String[] dateElement = date.split("-");
        int week = DateConversion.getWeek(date);
        System.out.println(week);

        ArrayList<RDV> ListeRDV = new ArrayList<>();
        String sql = "SELECT * FROM rdv WHERE "+typeId+" = '"+id+"' AND status = 1 "+
                "AND (4*(CAST(SUBSTR(date,6,2) as decimal)-1)+1+CAST(SUBSTR(date,9,2) as decimal)/7) == "+week+";";
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                RDV rdv = new RDV();
                rdv.archive=(rs.getBoolean("archive"));
                rdv.libelle=(rs.getString("libelle"));
                rdv.lieu=(rs.getString("lieu"));
                // rdv.creneau.id_prof = rs.getInt("id_prof"); NullPointerException
                rdv.enseignant=(GetterUser.getInfoProf(rs.getInt("id_prof")));
                rdv.etudiant = ((getInfoEleve(rs.getInt("id_eleve")))); NullPointerException
                ListeRDV.add(rdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(ListeRDV.size());
        return ListeRDV;
    }

    public static int getId(Utilisateur user){

        String nom = user.nom;
        String prenom = user.prenom;
        String mail = user.mail;
        Boolean typeUser = user instanceof Etudiant;

        String table;
        if (typeUser){
            table="eleve";
        } else {
            table="prof";
        }

        int id = 0;
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT id FROM "+table+" WHERE nom = '"+nom+"' AND prenom = '"+prenom+"' AND mail = '"+mail+"';";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                id = rs.getInt("id");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
