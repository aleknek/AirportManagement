package controllers;

import bin.FlightClass;
import bin.Gender;
import bin.Passenger;
import dao.FlightDAO;
import dao.FlightWorkerDAO;
import dao.PassengerDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PassengerCheckFields {

    public static boolean checkFields(HttpServletRequest req, String firstName, String lastName, String nationality, String passport,
                                      String dateOfBirthday, String gender, String flightClass){

        if (firstName == null || firstName.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "firstNameIsEmpty");
            return false;
        } else if (lastName == null || lastName.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "lastNameIsEmpty");
            return false;
        } else if (nationality == null || nationality.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "nationalityIsEmpty");
            return false;
        } else if (passport == null || passport.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "passportIsEmpty");
            return false;
        } else if (dateOfBirthday == null || dateOfBirthday.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "dateOfBirthdayIsEmpty");
            return false;
        } else if (gender == null || gender.isEmpty()) {
            req.setAttribute("ErrorMessage", "genderIsEmpty");
            return false;
        } else if (flightClass == null || flightClass.trim().isEmpty()) {
            req.setAttribute("ErrorMessage", "flightClassIsEmpty");
            return false;
        }

        return true;
    }

    public static void setAttributesAndViewError(HttpServletRequest req, HttpServletResponse resp, String idFlight,
                                                 String firstName, String lastName, String nationality, String passport,
                                                 String dateOfBirthday, String gender, String flightClass, String idPassenger,
                                                 String currentPage) throws ServletException, IOException {

        if (currentPage.equals("passengerModifying.jsp")){
            Passenger passenger = new PassengerDAO().loadPassengerById(Integer.valueOf(idPassenger));
            req.setAttribute("passenger", passenger);
        }

        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);
        req.setAttribute("nationality", nationality);
        req.setAttribute("passport", passport);
        req.setAttribute("dateOfBirthday", dateOfBirthday);
        req.setAttribute("gender", gender);
        req.setAttribute("flightClass", flightClass);

        req.setAttribute("idFlight", idFlight);
        req.setAttribute("classes", FlightClass.values());
        req.setAttribute("genders", Gender.values());

        req.setAttribute("performance", FlightWorkerDAO.getPerformance(new FlightDAO().loadFlightById(Integer.valueOf(idFlight))));
        req.setAttribute("currentPage", currentPage);
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

}
