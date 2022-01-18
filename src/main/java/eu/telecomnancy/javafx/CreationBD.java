package eu.telecomnancy.javafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreationBD {

    public static void main(String[] args)
    {
        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:BDD/TNRDV.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists connection");
            statement.executeUpdate("drop table if exists admin");
            statement.executeUpdate("drop table if exists eleve");
            statement.executeUpdate("drop table if exists prof");
            statement.executeUpdate("drop table if exists rdv");
            statement.executeUpdate("CREATE TABLE connection(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  mail TEXT NOT NULL,\n" +
                    "  MDP TEXT NOT NULL,\n" +
                    "  typeUser TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE admin(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  nom TEXT NOT NULL,\n" +
                    "  prenom TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE eleve(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  nom TEXT NOT NULL,\n" +
                    "  prenom TEXT NOT NULL,\n" +
                    "  sexe INTEGER NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  adresse TEXT NOT NULL,\n" +
                    "  mail TEXT NOT NULL,\n" +
                    "  telephone TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE prof(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  nom TEXT NOT NULL,\n" +
                    "  prenom TEXT NOT NULL,\n" +
                    "  sexe INTEGER NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  adresse TEXT NOT NULL,\n" +
                    "  mail TEXT NOT NULL,\n" +
                    "  telephone TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE rdv(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  id_prof INTEGER NOT NULL,\n" +
                    "  id_eleve INTEGER NOT NULL,\n" +
                    "  salle INTEGER NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  heure TEXT NOT NULL,\n" +
                    "  statut INTEGER NOT NULL,\n" +
                    "  PRIMARY KEY (id_prof, id_eleve, date, heure))");
            statement.executeUpdate("insert into admin values(2,'hey' ,'yui')");
            ResultSet rs = statement.executeQuery("select * from admin");
            while(rs.next())
            {
                // read the result set
                System.out.println("name = " + rs.getString("nom"));
                System.out.println("id = " + rs.getInt("prenom"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
