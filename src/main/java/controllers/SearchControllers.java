package controllers;

import bean.Flight;
import bean.TypeOfFlight;
import dao.CityDAO;
import dao.FlightDAO;
import dao.PassengerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchControllers", urlPatterns = {"/staff/search", "/staff/search/", "/passenger/search", "/passenger/search/"})
public class SearchControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String chooserTypeSearch = req.getParameter("chooserTypeSearch");
        if (req.getParameter("chooserTypeSearch") == null) {
            chooserTypeSearch = "flight number";
        }

        List<String> typesSearch = new ArrayList<String>();

        if (req.getSession().getAttribute("CURRENT_ROLE").toString().equals("1")){
            typesSearch.add("flight number");
            typesSearch.add("price");
            typesSearch.add("first and second name");
            typesSearch.add("passport");
            typesSearch.add("arrival port");
            typesSearch.add("departure port");
        } else {
            typesSearch.add("flight number");
            typesSearch.add("price");
            typesSearch.add("arrival port");
            typesSearch.add("departure port");
        }

        req.setAttribute("cities", new CityDAO().getCities());
        req.setAttribute("typesOfFlight", TypeOfFlight.values());
        req.setAttribute("chooserTypeSearch", chooserTypeSearch);
        req.setAttribute("typesSearch", typesSearch);
        req.setAttribute("currentPage", "search.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String flightNumber = req.getParameter("flightNumber");
        String price = req.getParameter("price");
        String currentTypeOfFlight = req.getParameter("currentTypeOfFlight");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String passport = req.getParameter("passport");
        String arrivalPort = req.getParameter("arrivalPort");
        String departurePort = req.getParameter("departurePort");

        if (flightNumber != null) {
            if (!flightNumber.trim().isEmpty()) {

                Flight flight = new FlightDAO().loadFlightByNumber(flightNumber.trim());
                List<Flight> flights = new ArrayList<Flight>();
                flights.add(flight);

                setDataForFlightJSP(req, resp, flights, currentTypeOfFlight);
            }
        }

        if (price != null) {
            if (!price.isEmpty()) {
                List<Flight> flights = new FlightDAO().loadFlightsByPrice(price, TypeOfFlight.valueOf(currentTypeOfFlight));

                setDataForFlightJSP(req, resp, flights, currentTypeOfFlight);
            }
        }

        if (firstName != null && secondName != null) {
            if (!firstName.isEmpty() && !secondName.isEmpty()) {

                List<Flight> flights = new FlightDAO().loadFlightsByIds(
                        new PassengerDAO().loadPassengerByFirstNameAndLastName(firstName, secondName),
                        TypeOfFlight.valueOf(currentTypeOfFlight));

                setDataForFlightJSP(req, resp, flights, currentTypeOfFlight);
            }
        }

        if (passport != null) {
            if (!passport.isEmpty()) {

                List<Flight> flights = new FlightDAO().loadFlightsByIds(
                        new PassengerDAO().getIdsFlightByPassportOfPassenger(passport),
                        TypeOfFlight.valueOf(currentTypeOfFlight));

                setDataForFlightJSP(req, resp, flights, currentTypeOfFlight);
            }
        }

        if (arrivalPort != null) {
            if (!arrivalPort.isEmpty()) {
                List<Flight> flights = new FlightDAO().loadFlightsByNameOfCity(arrivalPort, TypeOfFlight.arrival);
                setDataForFlightJSP(req, resp, flights, TypeOfFlight.arrival.name());
            }
        }

        if (departurePort != null) {
            if (!departurePort.isEmpty()) {
                List<Flight> flights = new FlightDAO().loadFlightsByNameOfCity(departurePort, TypeOfFlight.departure);
                setDataForFlightJSP(req, resp, flights, TypeOfFlight.departure.name());
            }
        }
    }

    public void setDataForFlightJSP(HttpServletRequest req, HttpServletResponse resp, List<Flight> flights, String currentTypeOfFlight) throws ServletException, IOException {
        req.setAttribute("typeOfFlight", TypeOfFlight.valueOf(currentTypeOfFlight));
        req.setAttribute("flights", flights);
        req.setAttribute("currentPage", "flights.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }
}
