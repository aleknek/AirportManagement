package controllers;

import bin.FlightStatus;
import bin.TypeOfFlight;
import dao.CityDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FlightCheckFields {

    public static boolean checkFields(HttpServletRequest req, String flightNumber, String city, String date, String terminal,
                                      String gate, String status, String typeOfFlight) {

        if (flightNumber == null || flightNumber.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "flightNumberIsEmpty");
            return false;
        } else if (city == null || city.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "cityIsEmpty");
            return false;
        } else if (date == null || date.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "dateIsEmpty");
            return false;
        } else if (terminal == null || terminal.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "terminalIsEmpty");//
            return false;
        } else if (gate == null || gate.trim().isEmpty()) {
            if (typeOfFlight != null){
                if (typeOfFlight.equals("arrival")){
                    return true;
                }
            }
            req.setAttribute("ErrorMessage", "gateIsEmpty");
            return false;
        } else if (status == null || status.isEmpty()) {
            req.setAttribute("ErrorMessage", "statusIsEmpty");
            return false;
        } else if (typeOfFlight == null || typeOfFlight.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "typeOfFlightIsEmpty");
            return false;
        }

        return true;
    }

    public static void setCommonAttributes(HttpServletRequest req, HttpServletResponse resp,
                                           String flightNumber, String city, String date, String terminal,
                                           String gate, String status, String typeOfFlight, String currentPage) throws ServletException, IOException {

        req.setAttribute("flightNumber", flightNumber);
        req.setAttribute("city", city);
        req.setAttribute("date", date);
        req.setAttribute("terminal", terminal);
        req.setAttribute("gate", gate);
        req.setAttribute("status", status);
        req.setAttribute("typeOfFlight", typeOfFlight);

        req.setAttribute("cities", new CityDAO().getCities());
        req.setAttribute("statuses", FlightStatus.values());
        req.setAttribute("typesOfFlights", TypeOfFlight.values());
        req.setAttribute("currentPage", currentPage);
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }
}
