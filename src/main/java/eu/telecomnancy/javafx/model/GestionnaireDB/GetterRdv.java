package eu.telecomnancy.javafx.model.GestionnaireDB;

import eu.telecomnancy.javafx.ConnectionClass;
import eu.telecomnancy.javafx.model.RDV;
import eu.telecomnancy.javafx.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class GetterRdv {

    public GetterRdv(){}

    /** Renvoie la liste des rdv pour le user pour la semaine de la date en entrée**/
    public ArrayList<RDV> getRDVWeek(Utilisateur user, String date) throws SQLException {

        int id = getId(user);

        String typeId;
        if (user.etudiant){
            typeId="id_eleve";
        } else {
            typeId="id_prof";
        }
        String[] dateElement = date.split("/");
        int week = getWeek(date);
        System.out.println(week);

        ArrayList<RDV> ListeRDV = new ArrayList<>();
        String sql = "SELECT * FROM rdv WHERE "+typeId+" = '"+id+"' AND status = 1 "+
                "AND (4*(CAST(SUBSTR(date,4,2) as decimal)-1)+1+CAST(SUBSTR(date,1,2) as decimal)/7) == "+week+";";
        try {
            Connection connection = ConnectionClass.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("l77");
            while (rs.next()){
                System.out.println("l79");
                RDV rdv = new RDV();
                rdv.setArchive(rs.getBoolean("archive"));
                rdv.setLibelle(rs.getString("libelle"));
                rdv.setLieu(rs.getString("lieu"));
                System.out.println("l82");
                // rdv.creneau.id_prof = rs.getInt("id_prof"); NullPointerException

                System.out.println("l85");
                rdv.setEnseignant(getInfoProf(rs.getInt("id_prof")));
                //rdv.setEtudiants((getInfoEleve(rs.getInt("id_eleve")))); NullPointerException
                System.out.println("l86");
                ListeRDV.add(rdv);
                System.out.println("l87");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(ListeRDV.size());
        return ListeRDV;
    }

    public int getId(Utilisateur user){

        String nom = user.nom;
        String prenom = user.prenom;
        String mail = user.mail;
        Boolean typeUser = user.etudiant;

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
