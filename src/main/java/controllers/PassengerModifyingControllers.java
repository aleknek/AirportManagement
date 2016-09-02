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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet(name = "PassengerModifyingControllers", urlPatterns = {"/staff/passengerModifying", "/staff/passengerModifying/"})
public class PassengerModifyingControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  button press "Modify passenger data"
        List<Integer> ids = new ArrayList<Integer>(); // get id flight and id modification passenger
        StringTokenizer tokenizer = new StringTokenizer(req.getParameter("idFlightAndIdPassenger"), ", ");

        while (tokenizer.hasMoreElements()) {
            ids.add(Integer.parseInt(tokenizer.nextToken()));
        }

        int idPassenger = ids.get(1);
        int currentIdFlight = ids.remove(0);

        FlightDAO flightDAO = new FlightDAO();
        Passenger passenger = new PassengerDAO().loadPassengerById(Integer.valueOf(idPassenger));

        req.setAttribute("passenger", passenger);
        req.setAttribute("idFlight", currentIdFlight);
        req.setAttribute("genders", Gender.values());
        req.setAttribute("classes", FlightClass.values());

        req.setAttribute("performance", FlightWorkerDAO.getPerformance(flightDAO.loadFlightById(Integer.valueOf(currentIdFlight))));
        req.setAttribute("currentPage", "passengerModifying.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  modifying chooser passenger by pressing button "Apply"
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String idFlight = req.getParameter("idFlight");
        String idPassenger = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String nationality = req.getParameter("nationality");
        String passport = req.getParameter("passport");
        String dateOfBirthday = req.getParameter("dateOfBirthday");
        String selectedGender = req.getParameter("selectedGender");
        String selectedClass = req.getParameter("selectedClass");

        if (PassengerCheckFields.checkFields(req, firstName, lastName, nationality, passport, dateOfBirthday,
                selectedGender, selectedClass)) {

            String idGender = String.valueOf(Gender.valueOf(selectedGender).ordinal() + 1);
            String idClass = String.valueOf(FlightClass.valueOf(selectedClass).ordinal() + 1);

            String[] values = {firstName, lastName, nationality, passport, dateOfBirthday, idGender, idPassenger};
            new PassengerDAO().passengerModifying(values, idFlight, idClass, idPassenger);

            req.getSession().setAttribute("idFlight", idFlight);
            resp.sendRedirect(req.getSession().getAttribute("CURRENT_MAPPING").toString() + "/passengers");

        } else {
            PassengerCheckFields.setAttributesAndViewError(req, resp, idFlight, firstName, lastName, nationality, passport, dateOfBirthday,
                    selectedGender, selectedClass, idPassenger, "passengerModifying.jsp");
        }
    }
}
