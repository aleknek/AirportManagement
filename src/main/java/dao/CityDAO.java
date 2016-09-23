package dao;

import java.util.List;

import bin.City;
import database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityDAO {

    private static ResultSet rs;

    public int getIdCity(String cityName) {

        try {

            String sqlQuery;

            sqlQuery = "SELECT city.id AS id FROM db_airport.cities AS city " +
                    "WHERE city.name = " + "'" + cityName + "'";

            PreparedStatement preparedStatement = DBConnection.getInstance().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                createNewCity(cityName);
                return getIdCity(cityName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<City> getCities() {

        List<City> cities = new ArrayList<City>();

        try {

            String sqlQuery;

            sqlQuery = "SELECT * FROM db_airport.cities";

            PreparedStatement preparedStatement = DBConnection.getInstance().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                cities.add(city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public void createNewCity(String cityName) {

        String sqlQuery = "INSERT INTO db_airport.cities(name) values (" + "'" + cityName + "'" + ")";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnection.getInstance().getConn().prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}