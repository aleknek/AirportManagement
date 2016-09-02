package controllers;

import dao.RoleDAO;
import bin.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogoutController", urlPatterns = {"/logout", "/logout/"})
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Role> roles = new RoleDAO().loadAllRoles();
        req.setAttribute("roles", roles);

        req.getSession().invalidate();
        req.setAttribute("currentPage", "login.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }
}
