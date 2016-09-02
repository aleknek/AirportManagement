package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeController", urlPatterns = {"/staff/home", "/passenger/home", "/staff/home/", "/passenger/home/"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("currentPage", "home.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

}
