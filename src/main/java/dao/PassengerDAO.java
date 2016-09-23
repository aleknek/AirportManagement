package dao;

import bin.FlightClass;
import bin.Gender;
import bin.Passenger;
import database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PassengerDAO {

    private static ResultSet rs;

    public List<Passenger> loadPassengersByIdFlight(int idFlight) {

        List<Passenger> passengers = new ArrayList<Passenger>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            String sqlQuery;
            sqlQuery = "SELECT pass.firstName, pass.lastName, pass.nationality, pass.passport, class_flight.name AS class, " +
                    "gender.name AS sex, pass.dateOfBirthday, pass.id  FROM tickets " +
                    "LEFT JOIN passenger AS pass " +
                    "ON id_passenger = pass.id " +
                    "LEFT JOIN class_flight " +
                    "ON id_class = class_flight.id " +
                    "LEFT JOIN gender " +
                    "ON id_gender = gender.id " +
                    "WHERE id_flight = " + idFlight;

            PreparedStatement preparedStatement = DBConnection.getInstance().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(rs.getInt("id"));
                passenger.setFirstName(rs.getString("firstName"));
                passenger.setLastName(rs.getString("lastName"));
                passenger.setNationality(rs.getString("nationality"));
                passenger.setPassport(rs.getString("passport"));
                passenger.setFlightClass(FlightClass.valueOf(rs.getString("class")));
                passenger.setDateOfBirthday(dateFormat.format(rs.getDate("dateOfBirthday")));
                passenger.setGender(Gender.valueOf(rs.getString("sex")));
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    public Passenger loadPassengerById(int idPassenger) {

        Passenger passenger = new Passenger();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            String sqlQuery;
            sqlQuery = "SELECT pass.firstName, pass.lastName, pass.nationality, pass.passport, class_flight.name AS class, " +
                    "gender.name AS sex, pass.dateOfBirthday, pass.id  FROM tickets " +
                    "LEFT JOIN passenger AS pass " +
                    "ON id_passenger = pass.id " +
                    "LEFT JOIN class_flight " +
                    "ON id_class = class_flight.id " +
                    "LEFT JOIN gender " +
                    "ON id_gender = gender.id " +
                    "WHERE pass.id = " + idPassenger;

            PreparedStatement preparedStatement = DBConnection.getInstance().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                passenger.setId(rs.getInt("id"));
                passenger.setFirstName(rs.getString("firstName"));
                passenger.setLastName(rs.getString("lastName"));
                passenger.setNationality(rs.getString("nationality"));
                passenger.setPassport(rs.getString("passport"));
                passenger.setFlightClass(FlightClass.valueOf(rs.getString("class")));
                passenger.setDateOfBirthday(dateFormat.format(rs.getDate("dateOfBirthday")));
                passenger.setGender(Gender.valueOf(rs.getString("sex")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    public List<String> loadPassengerByFirstNameAndLastName(String firstName, String lastName) {

        List<String> arrayIds = new ArrayList<String>();

        try {

            String sqlQuery;
            sqlQuery = "SELECT tickets.id_flight FROM db_airport.passenger AS passenger " +
                    "LEFT JOIN db_airport.tickets AS tickets " +
                    "ON passenger.id = tickets.id_passenger " +
                    "WHERE passenger.firstName = " + "'" + firstName + "' AND passenger.lastName = '" + lastName + "'";

            PreparedStatement preparedStatement = DBConnection.getInstance().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                arrayIds.add(String.valueOf(rs.getInt("id_flight")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayIds;
    }

    public List<String> getIdsFlightByPassportOfPassenger(String passport) {

        List<String> arrayIds = new ArrayList<String>();

        try {

            String sqlQuery;
            sqlQuery = "SELECT tickets.id_flight FROM db_airport.passenger AS passenger " +
                    "LEFT JOIN db_airport.tickets AS tickets " +
                    "ON passenger.id = tickets.id_passenger " +
                    "WHERE passenger.passport = " + "'" + passport + "'";

            PreparedStatement preparedStatement = DBConnection.getInstance().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                arrayIds.add(String.valueOf(rs.getInt("id_flight")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayIds;
    }

    public void deletePassengers(List<Integer> idsPassengers) {

        DBConnection dbConnection = DBConnection.getInstance();

        for (Iterator<Integer> iterator = idsPassengers.iterator(); iterator.hasNext(); ) {
            String id = iterator.next().toString();
            String[] values = {id};
            dbConnection.runPrepareStatement("DELETE FROM db_airport.tickets WHERE id_passenger = ?", values, 0);
            dbConnection.runPrepareStatement("DELETE FROM db_airport.passenger WHERE id = ?", values, 0);
        }
    }

    public void passengerCreating(Passenger passenger, String idFlight) {

        DBConnection dbConnection = DBConnection.getInstance();

        String[] values = {passenger.getFirstName(), passenger.getLastName(), passenger.getNationality(), passenger.getPassport(),
                passenger.getDateOfBirthday(), String.valueOf(passenger.getGender().ordinal() + 1)};
        dbConnection.runPrepareStatement("INSERT INTO db_airport.passenger(firstName, lastName, nationality, passport," +
                " dateOfBirthday, id_gender) values (?, ?, ?, ?, ?, ?)", values, 0);


        String idPassenger = String.valueOf(getIdPassengerByNumberPassport(passenger.getPassport()));
        String[] valuesOfTickets = {idPassenger, idFlight, String.valueOf(passenger.getFlightClass().ordinal() + 1)};
        dbConnection.runPrepareStatement("INSERT INTO db_airport.tickets(id_passenger, id_flight, id_class) values (?, ?, ?)", valuesOfTickets, 0);

    }

    public void passengerModifying(String[] valuesForUpdatePassenger, String idFlight, String idClass, String id) {

        DBConnection dbConnection = DBConnection.getInstance();

        dbConnection.runPrepareStatement("UPDATE passenger SET firstName = ?, lastName = ?, nationality = ?, passport = ?," +
                " dateOfBirthday = ?, id_gender = ? WHERE id= ?;", valuesForUpdatePassenger, Integer.valueOf(id));

        String[] valuesForUpdateGlass = {idClass, id, idFlight};
        dbConnection.runPrepareStatement("UPDATE tickets SET id_class = ? WHERE id_passenger= ? AND id_flight= ?;", valuesForUpdateGlass, 0);

    }

    public int getIdPassengerByNumberPassport(String numberPassport) {

        try {

            String sqlQuery;
            sqlQuery = "SELECT id FROM db_airport.passenger WHERE passport = " + "'" + numberPassport + "'";

            PreparedStatement preparedStatement = DBConnection.getInstance().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
