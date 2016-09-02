package dao;

import bin.Flight;
import bin.TypeOfFlight;
import database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FlightDAO {

    private static ResultSet rs;

    public FlightDAO() {
    }

    public List<Flight> loadFlightsByTypeOfFlight(TypeOfFlight typeOfFlight) {

        List<Flight> flights = new LinkedList<Flight>();

        try {

            String sqlQuery;
            sqlQuery = "SELECT flights.id AS id, flights.terminal, flights.number AS number, flights.date AS date, " +
                    "flights.gate AS gate, statuses.name AS statusOfFlight, city.name AS city, type_flight.name AS type_flight " +
                    "FROM db_airport.flights AS flights " +
                    "LEFT JOIN db_airport.cities AS city " +
                    "ON id_city = city.id " +
                    "LEFT JOIN db_airport.statuses AS statuses " +
                    "ON id_status = statuses.id " +
                    "LEFT JOIN db_airport.type_flight AS type_flight " +
                    "ON id_type_flight = type_flight.id " +
                    "WHERE flights.id_type_flight =  " + (typeOfFlight.ordinal() + 1) + " " +
                    "ORDER BY date";

            PreparedStatement preparedStatement = new DBConnection().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight = FlightWorkerDAO.fillNewObject(flight, rs);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public List<Flight> loadAllFlights() {

        List<Flight> flights = new LinkedList<Flight>();

        try {

            String sqlQuery;
            sqlQuery = "SELECT flights.id AS id, flights.terminal, flights.number AS number, flights.date AS date, " +
                    "flights.gate AS gate, statuses.name AS statusOfFlight, city.name AS city, type_flight.name AS type_flight " +
                    "FROM db_airport.flights AS flights " +
                    "LEFT JOIN db_airport.cities AS city " +
                    "ON id_city = city.id " +
                    "LEFT JOIN db_airport.statuses AS statuses " +
                    "ON id_status = statuses.id " +
                    "LEFT JOIN db_airport.type_flight AS type_flight " +
                    "ON id_type_flight = type_flight.id " +
                    "ORDER BY city";

            PreparedStatement preparedStatement = new DBConnection().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight = FlightWorkerDAO.fillNewObject(flight, rs);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public Flight loadFlightById(int id) {

        Flight flight = new Flight();

        try {

            String sqlQuery;
            sqlQuery = "SELECT flights.id AS id, flights.terminal, flights.number AS number, flights.date AS date, " +
                    "flights.gate AS gate, statuses.name AS statusOfFlight, city.name AS city, type_flight.name AS type_flight " +
                    "FROM db_airport.flights AS flights " +
                    "LEFT JOIN db_airport.cities AS city " +
                    "ON id_city = city.id " +
                    "LEFT JOIN db_airport.statuses AS statuses " +
                    "ON id_status = statuses.id " +
                    "LEFT JOIN db_airport.type_flight AS type_flight " +
                    "ON id_type_flight = type_flight.id " +
                    "WHERE flights.id = " + id;

            PreparedStatement preparedStatement = new DBConnection().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                flight = FlightWorkerDAO.fillNewObject(flight, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public Flight loadFlightByNumber(String numberFlight) {

        Flight flight = new Flight();

        try {

            String sqlQuery;
            sqlQuery = "SELECT flights.id AS id, flights.terminal, flights.number AS number, flights.date AS date, " +
                    "flights.gate AS gate, statuses.name AS statusOfFlight, city.name AS city, type_flight.name AS type_flight " +
                    "FROM db_airport.flights AS flights " +
                    "LEFT JOIN db_airport.cities AS city " +
                    "ON id_city = city.id " +
                    "LEFT JOIN db_airport.statuses AS statuses " +
                    "ON id_status = statuses.id " +
                    "LEFT JOIN db_airport.type_flight AS type_flight " +
                    "ON id_type_flight = type_flight.id " +
                    "WHERE flights.number = " + "'" + numberFlight + "'";

            PreparedStatement preparedStatement = new DBConnection().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                flight = FlightWorkerDAO.fillNewObject(flight, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public List<Flight> loadFlightsByIds(List<String> idsFlights, TypeOfFlight typeOfFlight) {

        List<Flight> flights = new LinkedList<Flight>();

        String lineIdsForSqlQuery = "";
        Iterator<String> iterator = idsFlights.iterator();
        while (iterator.hasNext()) {
            lineIdsForSqlQuery = lineIdsForSqlQuery + "'" + iterator.next() + "'";
        }

        try {

            String sqlQuery;
            sqlQuery = "SELECT flights.id AS id, flights.terminal, flights.number AS number, flights.date AS date, " +
                    "flights.gate AS gate, statuses.name AS statusOfFlight, city.name AS city, type_flight.name AS type_flight " +
                    "FROM db_airport.flights AS flights " +
                    "LEFT JOIN db_airport.cities AS city " +
                    "ON id_city = city.id " +
                    "LEFT JOIN db_airport.statuses AS statuses " +
                    "ON id_status = statuses.id " +
                    "LEFT JOIN db_airport.type_flight AS type_flight " +
                    "ON id_type_flight = type_flight.id " +
                    "WHERE flights.id IN (" + lineIdsForSqlQuery + ") AND flights.id_type_flight =  " + (typeOfFlight.ordinal() + 1);

            PreparedStatement preparedStatement = new DBConnection().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight = FlightWorkerDAO.fillNewObject(flight, rs);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public List<Flight> loadFlightsByNameOfCity(String nameCity, TypeOfFlight typeOfFlight) {

        List<Flight> flights = new ArrayList<Flight>();

        try {

            String sqlQuery;
            sqlQuery = "SELECT flights.id AS id, flights.terminal, flights.number AS number, flights.date AS date, " +
                    "flights.gate AS gate, statuses.name AS statusOfFlight, city.name AS city, type_flight.name AS type_flight " +
                    "FROM db_airport.flights AS flights " +
                    "LEFT JOIN db_airport.cities AS city " +
                    "ON id_city = city.id " +
                    "LEFT JOIN db_airport.statuses AS statuses " +
                    "ON id_status = statuses.id " +
                    "LEFT JOIN db_airport.type_flight AS type_flight " +
                    "ON id_type_flight = type_flight.id " +
                    "WHERE city.name = '" + nameCity + "' AND flights.id_type_flight =  " + (typeOfFlight.ordinal() + 1);

            PreparedStatement preparedStatement = new DBConnection().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight = FlightWorkerDAO.fillNewObject(flight, rs);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public List<Flight> loadFlightsByPrice(String price, TypeOfFlight typeOfFlight) {

        List<Flight> flights = new ArrayList<Flight>();

        try {

            String sqlQuery;
            sqlQuery = "SELECT flights.id AS id, flights.terminal, flights.number AS number, flights.date AS date, " +
                    "flights.gate AS gate, statuses.name AS statusOfFlight, city.name AS city, type_flight.name AS type_flight " +
                    "FROM db_airport.flights AS flights " +
                    "LEFT JOIN db_airport.cities AS city " +
                    "ON id_city = city.id " +
                    "LEFT JOIN db_airport.statuses AS statuses " +
                    "ON id_status = statuses.id " +
                    "LEFT JOIN db_airport.type_flight AS type_flight " +
                    "ON id_type_flight = type_flight.id " +
                    "LEFT join db_airport.price AS price " +
                    "ON flights.id = price.id_flight " +
                    "WHERE price.price =  " + price + " AND type_flight.id = " + (typeOfFlight.ordinal() + 1);

            PreparedStatement preparedStatement = new DBConnection().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight = FlightWorkerDAO.fillNewObject(flight, rs);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
