package controllers;

import bean.TypeOfFlight;
import dao.FlightDAO;
import bean.Flight;
import dao.FlightWorkerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet(name = "FlightsControllers", urlPatterns = {"/staff/flights", "/staff/flights/", "/passenger/flights", "/passenger/flights/", "/flights"})
public class FlightsControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // click on reference "Information flights"
        List<Flight> flights = new FlightDAO().loadFlightsByTypeOfFlight(TypeOfFlight.departure);
        req.setAttribute("typeOfFlight", "departure");
        req.setAttribute("flights", flights);

        req.setAttribute("currentPage", "flights.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // button press "Arrival" or "Departure"
        FlightDAO flightDAO = new FlightDAO();
        String typeOfFlight = req.getParameter("typeOfFlight");

        String idFlights = req.getParameter("idFlights");

        if (typeOfFlight != null) {
            List<Flight> flights = flightDAO.loadFlightsByTypeOfFlight(TypeOfFlight.valueOf(typeOfFlight));
            req.setAttribute("typeOfFlight", typeOfFlight);
            req.setAttribute("flights", flights);

            req.setAttribute("currentPage", "flights.jsp");
            req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);

        } else { // button press "Remove flight"

            List<Integer> idsFlights = new ArrayList<Integer>();
            StringTokenizer tokenizer = new StringTokenizer(idFlights, ", ");

            while (tokenizer.hasMoreElements()) {
                idsFlights.add(Integer.parseInt(tokenizer.nextToken()));
                new FlightWorkerDAO().deleteFlights(idsFlights);
                List<Flight> flights = flightDAO.loadFlightsByTypeOfFlight(TypeOfFlight.departure);
                req.setAttribute("typeOfFlight", "departure");
                req.setAttribute("flights", flights);
                req.setAttribute("currentPage", "flights.jsp");
                req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
            }
        }
    }
}
