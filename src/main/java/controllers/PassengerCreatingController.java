package controllers;

import bin.*;
import dao.FlightDAO;
import dao.FlightWorkerDAO;
import dao.PassengerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PassengerCreatingController", urlPatterns = {"/staff/passengerCreating", "/staff/passengerCreating/"})
public class PassengerCreatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  button press "Add a new passenger"
        String idFlight = req.getParameter("idFlight");

        req.setAttribute("idFlight", idFlight);
        req.setAttribute("classes", FlightClass.values());
        req.setAttribute("genders", Gender.values());

        req.setAttribute("performance", FlightWorkerDAO.getPerformance(new FlightDAO().loadFlightById(Integer.valueOf(idFlight))));
        req.setAttribute("currentPage", "passengerCreating.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  addition of a new passenger by pressing button "Create"
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String idFlight = req.getParameter("idFlight");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String nationality = req.getParameter("nationality");
        String passport = req.getParameter("passport");
        String dateOfBirthday = req.getParameter("dateOfBirthday");
        String gender = req.getParameter("selectedGender");
        String flightClass = req.getParameter("selectedClass");

        if (PassengerCheckFields.checkFields(req, firstName, lastName, nationality, passport, dateOfBirthday, gender, flightClass)) {

            Passenger passenger = new Passenger(firstName, lastName, nationality, passport, dateOfBirthday,
                    Gender.valueOf(gender), FlightClass.valueOf(flightClass));

            new PassengerDAO().passengerCreating(passenger, idFlight);

            req.getSession().setAttribute("idFlight", idFlight);
            resp.sendRedirect(req.getSession().getAttribute("CURRENT_MAPPING").toString() + "/passengers");

        } else {
            PassengerCheckFields.setAttributesAndViewError(req, resp, idFlight, firstName, lastName, nationality, passport, dateOfBirthday,
                    gender, flightClass, "", "passengerCreating.jsp");
        }
    }
}