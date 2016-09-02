package dao;

import bin.City;
import bin.Flight;
import bin.FlightStatus;
import bin.TypeOfFlight;
import constants.Constants;
import database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class FlightWorkerDAO {

    private static ResultSet rs;

    public static Flight fillNewObject(Flight flight, ResultSet rs) throws SQLException {

        City city = new City();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        flight.setId(rs.getInt("id"));
        flight.setNumber(rs.getString("number"));
        city.setName(rs.getString("city"));
        flight.setCity(city);
        flight.setTerminal(rs.getString("terminal"));
        flight.setGate(rs.getString("gate"));
        flight.setStatusOfFlight(FlightStatus.valueOf(rs.getString("statusOfFlight")));
        flight.setDateOfDeparture(dateFormat.format(rs.getTimestamp("date")));
        flight.setTypeOfFlight(TypeOfFlight.valueOf(rs.getString("type_flight")));

        return flight;
    }

    public static String getPerformance(Flight flight) {

        String performance = "";
        if (flight.getTypeOfFlight().ordinal() == 0) {
            performance = "Flight № " + flight.getNumber().toString() + ": " + flight.getCity().getName() + " " +
                    "- " + Constants.CURRENT_AIRPORT;
        } else {
            performance = "Flight № " + flight.getNumber().toString() + ":" +
                    " " + Constants.CURRENT_AIRPORT + " - " + flight.getCity().getName();
        }

        return performance;
    }

    public void flightModifying(String[] values, int id) {

        new DBConnection().runPrepareStatement("UPDATE flights SET number = ?, date = ?, terminal = ?, gate = ?, " +
                "id_status = ?, id_city = ?  WHERE id= ?;", values, id);
    }

    public void flightCreating(Flight flight) {

        String[] values = {flight.getNumber(), flight.getDateOfDeparture(), flight.getTerminal(),
                flight.getGate(), String.valueOf(flight.getStatusOfFlight().ordinal() + 1), String.valueOf(flight.getTypeOfFlight().ordinal() + 1),
                String.valueOf(new CityDAO().getIdCity(flight.getCity().getName()))};
        new DBConnection().runPrepareStatement("INSERT INTO db_airport.flights(number, date, terminal, gate, id_status, id_type_flight," +
                " id_city) values (?, ?, ?, ?, ?, ?, ?)", values, 0);
    }

    public void deleteFlights(List<Integer> idFlights) {

        DBConnection dbConnection = new DBConnection();

        for (Iterator<Integer> iterator = idFlights.iterator(); iterator.hasNext(); ) {
            String id = iterator.next().toString();
            String[] values = {id};
            dbConnection.runPrepareStatement("DELETE FROM db_airport.price WHERE id_flight = ?", values, 0);
            dbConnection.runPrepareStatement("DELETE FROM db_airport.tickets WHERE id_flight = ?", values, 0);
            dbConnection.runPrepareStatement("DELETE FROM db_airport.flights WHERE id = ?", values, 0);
        }
    }

}
