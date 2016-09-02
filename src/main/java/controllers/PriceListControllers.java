package controllers;

import dao.FlightDAO;
import dao.PriceDAO;
import bin.Flight;
import bin.PriceList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PriceListControllers", urlPatterns = {"/staff/priceList", "/staff/priceList/", "/passenger/priceList", "/passenger/priceList/"})
public class PriceListControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String chooser = req.getParameter("chooser");
        int currentId = (chooser == null ? 1 : Integer.valueOf(chooser));

        FlightDAO flightDAO = new FlightDAO();
        PriceDAO priceDAO = new PriceDAO();
        List<Flight> flights = flightDAO.loadAllFlights();
        Flight flight = flightDAO.loadFlightById(currentId);
        List<PriceList> priceLists = priceDAO.loadPriceListForCurrentFlight(flight);
        req.setAttribute("flights", flights);
        req.setAttribute("flight", flight);
        req.setAttribute("priceLists", priceLists);

        req.setAttribute("currentPage", "priceList.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
