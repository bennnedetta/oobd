package oobd2324_27.uninadelivery.unina_delivery.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgres {
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=uninadelivery_oo";
    private String user = "postgres";
    private String password = "pomodoriverdi";
    private static Connection connection;

    private Postgres() throws SQLException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()){
                new Postgres();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
