package controllers;

import bean.TypeOfFlight;
import dao.CityDAO;
import bean.City;
import bean.Flight;
import bean.FlightStatus;
import dao.FlightWorkerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FlightCreatingController", urlPatterns = {"/staff/flightCreating", "/staff/flightCreating/"})
public class FlightCreatingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  button press "Add a new flight"
        req.setAttribute("cities", new CityDAO().getCities());
        req.setAttribute("statuses", FlightStatus.values());
        req.setAttribute("typesOfFlights", TypeOfFlight.values());
        req.setAttribute("currentPage", "flightCreating.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  addition of a new flight by pressing button "Create"
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String flightNumber = req.getParameter("flight");
        String city = req.getParameter("selectedCity");
        String date = req.getParameter("date");
        String terminal = req.getParameter("terminal");
        String gate = req.getParameter("gate");
        String status = req.getParameter("selectedStatus");
        String typeOfFlight = req.getParameter("selectedType");

        if (FlightCheckFields.checkFields(req, flightNumber, city, date, terminal, gate, status, typeOfFlight)) {

            Flight flight = new Flight(flightNumber, terminal, gate, FlightStatus.valueOf(FlightStatus.getCurrentStatus(status)),
                    date, new City(city), TypeOfFlight.valueOf(typeOfFlight));

            new FlightWorkerDAO().flightCreating(flight);
            resp.sendRedirect(req.getSession().getAttribute("CURRENT_MAPPING").toString() + "/flights");

        } else {
            FlightCheckFields.setCommonAttributes(req, resp, flightNumber, city, date, terminal, gate, status, typeOfFlight, "flightCreating.jsp");
        }
    }
}