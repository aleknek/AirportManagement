package dao;

import bin.Flight;
import bin.FlightClass;
import bin.PriceList;
import database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceDAO {

    private static ResultSet rs;

    public List<PriceList> loadPriceListForCurrentFlight(Flight flight) {

        List<PriceList> priceLists = new ArrayList<PriceList>();
        try {

            String sqlQuery;
            sqlQuery = "SELECT name, price FROM db_airport.price " +
                    "LEFT JOIN class_flight ON " +
                    "id_class = class_flight.id " +
                    "WHERE id_flight = " + flight.getId();

            PreparedStatement preparedStatement = new DBConnection().getConn().prepareStatement(sqlQuery);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PriceList priceList = new PriceList();
                priceList.setFlight(flight);
                priceList.setFlightClass(FlightClass.valueOf(rs.getString("name")));
                priceList.setPrice(rs.getInt("price"));
                priceLists.add(priceList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceLists;
    }


}
