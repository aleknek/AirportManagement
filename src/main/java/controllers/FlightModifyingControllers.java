package controllers;

import dao.CityDAO;
import dao.FlightDAO;
import bean.Flight;
import bean.FlightStatus;
import dao.FlightWorkerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FlightModifyingControllers", urlPatterns = {"/staff/flightModifying", "/staff/flightModifying/"})
public class FlightModifyingControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  button press "Modify the data of the flight"
        String id = req.getParameter("id");

        req.setAttribute("id", id);
        req.setAttribute("performance", FlightWorkerDAO.getPerformance(new FlightDAO().loadFlightById(Integer.valueOf(id))));
        req.setAttribute("flight", new FlightDAO().loadFlightById(Integer.valueOf(id)));
        req.setAttribute("cities", new CityDAO().getCities());
        req.setAttribute("statuses", FlightStatus.values());

        req.setAttribute("currentPage", "flightModifying.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  modifying chooser flight by pressing button "Apply"
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        FlightDAO flightDAO = new FlightDAO();

        int id = Integer.valueOf(req.getParameter("id"));

        Flight flight = flightDAO.loadFlightById(id);

        String flightNumber = req.getParameter("flight");
        String city = req.getParameter("selectedCity");
        String date = req.getParameter("date");
        String terminal = req.getParameter("terminal");
        String gate = req.getParameter("gate");
        String status = req.getParameter("selectedStatus");

        if (FlightCheckFields.checkFields(req, flightNumber, city, date, terminal, gate, status, "doNotCheck")) {

            String idCity = String.valueOf(new CityDAO().getIdCity(city));
            String idStatus = String.valueOf(FlightStatus.valueOf(FlightStatus.getCurrentStatus(status)).ordinal() + 1);

            String[] values = {flightNumber, date, terminal, gate, idStatus, idCity, req.getParameter("id")};
            new FlightWorkerDAO().flightModifying(values, id);
            List<Flight> flights = flightDAO.loadFlightsByTypeOfFlight(flight.getTypeOfFlight());

            req.setAttribute("flight", flight);
            req.setAttribute("typeOfFlight", flight.getTypeOfFlight().name());
            req.setAttribute("flights", flights);
            req.setAttribute("currentPage", "flights.jsp");
            req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);

        } else {
            req.setAttribute("id", id);
            req.setAttribute("performance", FlightWorkerDAO.getPerformance(new FlightDAO().loadFlightById(Integer.valueOf(id))));
            req.setAttribute("flight", new FlightDAO().loadFlightById(Integer.valueOf(id)));
            FlightCheckFields.setCommonAttributes(req, resp, flightNumber, city, date, terminal, gate, status,
                    "", "flightModifying.jsp");
        }


    }
}
