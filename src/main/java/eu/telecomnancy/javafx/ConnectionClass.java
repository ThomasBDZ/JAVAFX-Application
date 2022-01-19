package eu.telecomnancy.javafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    private ConnectionClass() {

    }

    public static ConnectionClass getInstance(){
        return new ConnectionClass();
    }

    public Connection getConnection(){


        String connectDbString="jdbc:sqlite:BDD/TNRDV.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(connectDbString);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }

}
