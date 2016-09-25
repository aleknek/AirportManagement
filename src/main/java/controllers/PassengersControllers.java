package controllers;

import dao.FlightDAO;
import dao.FlightWorkerDAO;
import dao.PassengerDAO;
import bean.Flight;
import bean.Passenger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet(name = "PassengersControllers", urlPatterns = {"/staff/passengers", "/staff/passengers/", "/passenger/passengers", "/passenger/passengers/"})
public class PassengersControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  button press "View the list of passengers"
        String currentIdFlight = "";
        currentIdFlight = req.getParameter("idFlight");
        if (currentIdFlight == null || currentIdFlight.isEmpty()) {
            currentIdFlight = (String) req.getSession().getAttribute("idFlight");
        }

        setDataForPassengerJSP(req, resp, Integer.valueOf(currentIdFlight));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  button press "Remove the passenger"
        List<Integer> ids = new ArrayList<Integer>(); // get id flight and ids deleted passengers
        StringTokenizer tokenizer = new StringTokenizer(req.getParameter("idFlightAndIdPassengers"), ", ");

        while (tokenizer.hasMoreElements()) {
            ids.add(Integer.parseInt(tokenizer.nextToken()));
        }

        int currentIdFlight = ids.remove(0);

        new PassengerDAO().deletePassengers(ids);

        setDataForPassengerJSP(req, resp, Integer.valueOf(currentIdFlight));
    }

    public void setDataForPassengerJSP(HttpServletRequest req, HttpServletResponse resp, int currentIdFlight) throws ServletException, IOException {

        Flight flight = new FlightDAO().loadFlightById(currentIdFlight);
        List<Passenger> passengers = new PassengerDAO().loadPassengersByIdFlight(currentIdFlight);

        FlightDAO flightDAO = new FlightDAO();

        req.setAttribute("performance", FlightWorkerDAO.getPerformance(flightDAO.loadFlightById(currentIdFlight)));
        req.setAttribute("passengers", passengers);
        req.setAttribute("idFlight", flight.getId());
        req.setAttribute("currentPage", "passengers.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }
}
