package eu.telecomnancy.javafx;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

    public Connection connection;

    public Connection getConnection(){


        String dbName="TNRDV";
        String userName="root";
        String password="";

        try {

            connection = DriverManager.getConnection("jdbc:sqlite://localhost/"+dbName,userName,password);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return connection;
    }

}
