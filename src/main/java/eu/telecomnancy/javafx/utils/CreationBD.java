package eu.telecomnancy.javafx.utils;

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
            statement.executeUpdate("drop table if exists archive");
            statement.executeUpdate("drop table if exists classe");
            statement.executeUpdate("drop table if exists matiere");
            statement.executeUpdate("drop table if exists availableRDV");
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
                    "  sexe TEXT NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  adresse TEXT NOT NULL,\n" +
                    "  mail TEXT NOT NULL,\n" +
                    "  telephone TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE prof(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  nom TEXT NOT NULL,\n" +
                    "  prenom TEXT NOT NULL,\n" +
                    "  sexe TEXT NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  adresse TEXT NOT NULL,\n" +
                    "  mail TEXT NOT NULL,\n" +
                    "  telephone TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE archive(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  nom TEXT NOT NULL,\n" +
                    "  prenom TEXT NOT NULL,\n" +
                    "  sexe TEXT NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  adresse TEXT NOT NULL,\n" +
                    "  mail TEXT NOT NULL,\n" +
                    "  telephone TEXT NOT NULL,\n" +
                    "  typeUser TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE rdv(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  id_prof INTEGER NOT NULL,\n" +
                    "  id_dispo INTEGER NOT NULL,\n" +
                    "  id_eleve INTEGER NOT NULL,\n" +
                    "  status BOOLEAN NOT NULL,\n" +
                    "  archive BOOLEAN NOT NULL,\n" +
                    "  lieu TEXT NOT NULL,\n" +
                    "  libelle TEXT NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  heure TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id),\n" +
                    "  FOREIGN KEY (id_prof) REFERENCES prof(id),\n" +
                    "  FOREIGN KEY (id_eleve) REFERENCES eleve(id),\n" +
                    "  FOREIGN KEY (id_dispo) REFERENCES availableRDV(id))");
            statement.executeUpdate("CREATE TABLE availableRDV(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  id_prof INTEGER NOT NULL,\n" +
                    "  indice INTEGER NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id),\n" +
                    "  FOREIGN KEY (id_prof) REFERENCES prof(id))");
            statement.executeUpdate("CREATE TABLE classe(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  className TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE matiere(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  disciplineName TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("insert into connection values(1,'admin','admin','admin')");
            statement.executeUpdate("insert into connection values(2,'prof','prof','prof')");
            statement.executeUpdate("insert into connection values(3,'eleve','eleve','eleve')");
            statement.executeUpdate("insert into eleve values(1, 'eleve', 'eleve', 'M', '26/04/2000', '23 rue des tilleuls', 'eleve', '0638294739')");
            statement.executeUpdate("insert into eleve values(1, 'prof', 'prof', 'M', '26/04/2000', '23 rue des tilleuls', 'prof', '0638294739')");
            statement.executeUpdate("insert into connection values(3,'geroster@gmail.com','Gerald','prof')");
            statement.executeUpdate("insert into prof values(1, 'Gerald','Oster', 'M', '26/04/1980', '23 rue des collines', 'geroster@gmail.com', '0612343338')");
            statement.executeUpdate("insert into connection values(3,'sebdada@gmail.com','Dada','prof')");
            statement.executeUpdate("insert into prof values(2, 'Dada', 'Seb', 'M', '26/04/1980', '23 rue des montagnes', 'sebdada@gmail.com', '0612345678')");
            statement.executeUpdate("insert into connection values(3,'suzicoco@gmail.com','Collin','prof')");
            statement.executeUpdate("insert into prof values(3, 'Collin','Suzi', 'F', '26/04/1980', '23 rue des plaines', 'suzicoco@gmail.com', '0671029384')");
            statement.executeUpdate("insert into connection values(3,'benoitmartins@gmail.com','Martins','eleve')");
            statement.executeUpdate("insert into eleve values(1, 'Benoit', 'Martins', 'M', '26/04/2000', '23 rue des tilleuls', 'benoitmartins@gmail.com', '0638294739')");
            statement.executeUpdate("insert into connection values(3,'alicemerveille@gmail.com','Merveille','eleve')");
            statement.executeUpdate("insert into eleve values(2, 'Alice', 'Merveille', 'F', '26/04/2000', '23 rue des mers', 'alicemerveille@gmail.com', '0638114739')");
            statement.executeUpdate("insert into connection values(3,'eleve','Sel','eleve')");
            statement.executeUpdate("insert into eleve values(3, 'luciegoutsel@gmail.com', 'Sel', 'F', '26/04/2000', '23 rue des ecoles', 'luciegoutsel@gmail.com', '0638293339')");
            statement.executeUpdate("insert into availableRDV values(1, 1, 4, '06/06/2000')");
            statement.executeUpdate("insert into rdv values(1, 1, 1, 1, 1, 0, 'Le lieu est Nancy', 'Rdv reorientation', '06/06/2000', 10-00)");
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
