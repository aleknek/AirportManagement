package database;

import constants.Constants;

import java.sql.*;

public class DBConnection {

    private static Connection conn;
    private static DBConnection instance;

    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Constants.URL_TO_DATABASE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static synchronized DBConnection getInstance() {

        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public static void runPrepareStatement(String sqlQuery, String[] values, int id) {

        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            if (id != 0) {
                preparedStatement.setInt(1, id);
            }
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    preparedStatement.setString(i + 1, values[i].toString());
                }
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
